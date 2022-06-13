<template>
  <v-container>
    <v-row v-if="hasAccess == false">
      <v-col>
        <page403 />
      </v-col>
    </v-row>
    <v-row v-if="hasAccess">
      <v-col
        cols="12"
        md="6"
      >
        <v-simple-table>
          <template v-slot:default>
            <tbody>
              <tr>
                <td>Judul*</td>
                <td>
                  <v-textarea
                    v-model="hki.output.judul"
                    :error-messages="$v.hki.output.judul.$error ? ['Isian harus diisi'] : []"
                    :auto-grow="true"
                    rows="1"
                    counter="255"
                    :disabled="isUbah"
                  />
                </td>
              </tr>
              <tr>
                <td>Jenis HKI*</td>
                <td>
                  <v-select
                    v-model="hki.output.idJenisOutput"
                    :items="listJenisOutput"
                    type="text"
                    :error-messages="$v.hki.output.idJenisOutput.$error ? ['Pilihan harus dipilih'] : []"
                  />
                </td>
              </tr>
              <tr>
                <td>Tanggal Publikasi*</td>
                <td>
                  <v-menu
                    ref="menu"
                    v-model="menu"
                    :close-on-content-click="false"
                    transition="scale-transition"
                    offset-y
                    min-width="290px"
                  >
                    <template v-slot:activator="{ on }">
                      <v-text-field
                        v-model="computedTgl"
                        prepend-icon="mdi-calendar-range"
                        readonly
                        :error-messages="$v.hki.output.tanggalPublikasi.$error ? ['Tanggal harus diisi'] : []"
                        v-on="on"
                      />
                    </template>
                    <v-date-picker
                      ref="picker"
                      v-model="hki.output.tanggalPublikasi"
                      :max="new Date().toISOString().substr(0, 10)"
                      min="1900-01-01"
                      @change="save"
                    />
                  </v-menu>
                </td>
              </tr>
              <tr>
                <td>No. Pendaftaran*</td>
                <td>
                  <v-text-field
                    v-model="hki.noPendaftaran"
                    type="text"
                    :error-messages="$v.hki.noPendaftaran.$error ? ['Isian harus diisi'] : []"
                    counter="100"
                  />
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
      <v-col
        cols="12"
        md="6"
      >
        <v-simple-table>
          <template v-slot:default>
            <tbody>
              <tr>
                <td>Status*</td>
                <td>
                  <v-radio-group
                    v-model="hki.statusHki"
                    row
                    style="font-size: small"
                  >
                    <v-radio
                      v-for="kel in listStatus"
                      :key="kel.value"
                      :value="kel.value"
                      :label="kel.text"
                    />
                  </v-radio-group>
                </td>
              </tr>
              <tr>
                <td>No. HKI*</td>
                <td>
                  <v-text-field
                    v-model="hki.noHki"
                    :error-messages="$v.hki.noHki.$error ? ['Isian harus diisi'] : []"
                    type="text"
                    counter="100"
                  />
                </td>
              </tr>
              <tr>
                <td>URL*</td>
                <td>
                  <v-text-field
                    v-model="hki.output.url"
                    type="text"
                    counter="100"
                    :error-messages="$v.hki.output.url.$error ? ['Isian harus diisi'] : []"
                  />
                </td>
              </tr>
              <tr>
                <td>Berkas HKI <br>(pdf maks 5 MB)* {{rules}}</td>
                <td>
                  <v-file-input
                    ref="fileInput"
                    v-model="fileUpload"
                    label="Upload File"
                    accept="application/pdf"
                    :rules="rules"
                    required
                    :error-messages="fileUploadErrors"
                    @change="onFilePicked"
                    @input="$v.fileUpload.$touch()"
                    @blur="$v.fileUpload.$touch()"
                  />
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>
    <v-row
      class="mt-6"
      align="end"
      justify="end"
      v-if="hasAccess"
    >
      <v-dialog
        v-model="dialog"
        max-width="600px"
      >
        <template v-slot:activator="{ on }">
          <v-col class="text-left">
            <v-card-actions>
              <div class="flex-grow-1" />
              <v-col class="text-right mt-6">
                <v-btn
                  color="green"
                  class="mb-2 white--text mr-2"
                  @click="saveForm()"
                >
                  Simpan
                </v-btn>
                <v-btn
                  to="/hki-dosen"
                  color="red"
                  class="mb-2 white--text mr-2"
                >
                  Batal
                </v-btn>
              </v-col>
            </v-card-actions>
          </v-col>
        </template>
      </v-dialog>
    </v-row>
    <v-snackbar
      v-model="snackbar"
      :color="snackbarColor"
      :timeout="2000"
      :top="true"
    >
      {{ snackbarText }}
      <v-btn
        dark
        text
        @click="snackbar = false"
      >
        Close
      </v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
  import moment from 'moment'
  import axios from 'axios'
  import { apiServer } from '../../../setup-axios'
  import { required } from 'vuelidate/lib/validators'
  import CommonService from '../../../services/CommonService'

  export default {
    data: () => ({
      dialog: false,
      hki: {
        username: '',
        output: {},
        penulisOutput: {},
      },
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isUbah: false,
      menu: false,
      date: null,
      errors: false,
      listPeran: [],
      listJenisOutput: [],
      peneliti: {},
      fileUpload: null,
      listStatus: [
        { value: 'T', text: 'Terdaftar' },
        { value: 'G', text: 'Granted' },
      ],
      rules: [
        value =>
          !value ||
          value.size <= 5000000 ||
          'File harus bertipe pdf dengan ukuran kurang dari 5MB',
      ],
      hasAccess: null,
      fileError: null,
    }),

    components: {
      Page403: () => import('@/views/403.vue'),
    },

    computed: {
      computedTgl () {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.hki.tahunPelaksanaan = new Date(this.hki.output.tanggalPublikasi).getFullYear()
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.hki.dateOfBirthFormattedDtp = this.hki.output.tanggalPublikasi
        return this.hki.dateOfBirthFormattedDtp
          ? moment(this.hki.dateOfBirthFormattedDtp).format('DD-MM-YYYY')
          : ''
      },
      fileUploadErrors () {
        this.fileError = false
        const errors = []
        !this.fileUpload && errors.push('File harus diisi')
        this.fileUpload && this.fileUpload.size > 5000000 && errors.push('Ukuran file melebihi 5 MB')
        this.fileUpload && this.fileUpload.type !== 'application/pdf' && errors.push('File harus berupa pdf')
        if (errors.length > 0) this.fileError = true
        return errors
      },
    },

    watch: {
      menu (val) {
        val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
      },
    },

    created () {
      this.isUbah = this.$route.params.id !== '0'
      this.getData()
    },

    validations: {
      hki: {
        noPendaftaran: { required },
        statusHki: { required },
        noHki: { required },
        output: {
          judul: { required },
          tanggalPublikasi: { required },
          idJenisOutput: { required },
          url: { required },
        },
      },
      fileUpload: { required },
    },

    methods: {
      save (date) {
        this.$refs.menu.save(date)
      },
      getData () {
        this.isUbah ? this.getHki() : this.hasAccess = true
        axios.all([
          apiServer.get('/peran-penulis-output/all'),
          apiServer.get('/jenis-output/hki'),
          apiServer.get('/peneliti/user/' + this.$store.state.user.userId),
        ]).then(axios.spread((listPeran, listJenisOutput, peneliti) => {
          listPeran.data.forEach(this.populatePeran)
          listJenisOutput.data.forEach(this.populateJenisOutput)
          this.peneliti = peneliti.data
        })).catch(error => {
          console.log('Terdapat error:', error.response) // Logs out the error
        })
      },
      getHki () {
        axios.all([
          apiServer.get('/hki/detail/' + this.$route.params.id),
          apiServer.get('/output-file/' + this.$route.params.id),
        ]).then(axios.spread((response, response1) => {
          this.hki = response.data
          this.hasAccess = this.hki.idPenelitian === null || (this.hki.idPenelitian != null && this.hki.idPeranPeneliti === 1)
          this.formattingDate(this.hki)
          this.fileUpload = CommonService.dataURLtoFile(
            response1.data,
            this.hki.fileName
          )
          const fileReader = new FileReader()
          fileReader.readAsDataURL(this.fileUpload)
        })).catch(error => {
          console.log('Terdapat error:', error.response) // Logs out the error
        })
      },
      back () {
        this.$router.replace('/dosen')
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
      saveForm () {
        if (this.validateAll()) return
        this.isUbah ? this.updateData() : this.addData()
      },
      addData () {
        this.hki.output.timestampUpload = new Date()
        const formData = new FormData()
        formData.append('file', this.fileUpload)
        apiServer
          .post('/output-upload/hki', formData)
          .then(response => {
            this.hki.output.pathFile = response.data.filePath
            apiServer
              .post('/output', this.hki.output)
              .then(response => {
                this.hki.penulisOutput.idOutput = response.data
                this.hki.penulisOutput.idPeneliti = this.peneliti.id
                this.hki.idOutput = response.data
                this.hki.penulisOutput.username = this.$store.state.user.name
                this.hki.username = this.$store.state.user.name
                axios.all([
                  apiServer.post('/hki', this.hki),
                  apiServer.post('/penulis-output', this.hki.penulisOutput),
                ]).then(axios.spread((response1, response2) => {
                  this.showSnackbar('success', 'Data berhasil ditambah')
                  this.dialog = false
                  this.$router.replace('/hki-dosen')
                })).catch(error => {
                  console.log('Terdapat error:', error.response) // Logs out the error
                  this.showSnackbar(
                    'error',
                    'Terdapat Kesalahan'
                  )
                })
              })
              .catch(error => {
                console.log('There was an error:', error.response)
                this.showSnackbar('error', 'Terdapat Kesalahan')
                this.dialog = false
              })
          })
          .catch(error => {
            console.log('There was an error:', error.response)
          })
      },
      updateData () {
        if (this.fileUpload.name !== this.hki.fileName) {
          const formData = new FormData()
          formData.append('file', this.fileUpload)
          axios.all([
            apiServer.post('/output-upload/hki', formData),
            apiServer.delete('/output-file/' + this.hki.idOutput),
          ]).then(axios.spread((response1, response2) => {
            this.hki.output.pathFile = response1.data.filePath
            this.hki.output.timestampUpload = new Date()
            this.updateRowData()
          })).catch(error => {
            console.log('There was an error:', error.response1)
            console.log('There was an error:', error.response2)
            this.dialog = false
          })
        } else {
          this.updateRowData()
        }
      },
      updateRowData () {
        axios.all([
          apiServer.put('/hki', this.hki),
          apiServer.put('/penulis-output', this.hki.penulisOutput),
          apiServer.put('/output', this.hki.output),
        ]).then(axios.spread((response1, response2, response3) => {
          this.showSnackbar('success', 'Data berhasil ditambah')
          this.dialog = false
          this.$router.replace('/hki-dosen')
        })).catch(error => {
          console.log('Terdapat error:', error.response) // Logs out the error
          this.showSnackbar(
            'error',
            'Terdapat Kesalahan'
          )
        })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Terdapat Kesalahan')
            this.dialog = false
          })
      },
      validateAll () {
        this.$v.$touch()
        this.errors = this.$v.hki.$anyError || this.fileError || this.$v.hki.$invalid
        var validate = !!this.errors
        return validate
      },
      formattingDate (value) {
        value.dateOfBirthFormattedDtp = value.output.tanggalPublikasi
          ? moment(value.output.tanggalPublikasi).format('DD-MM-YYYY')
          : ''
        value.output.tanggalPublikasi = value.output.tanggalPublikasi
          ? moment(value.output.tanggalPublikasi).format('YYYY-MM-DD')
          : ''
      },
      populateJenisOutput (value) {
        this.listJenisOutput.push({ value: value.id, text: value.nama })
      },
      populatePeran (value) {
        this.listPeran.push({ value: value.id, text: value.nama })
      },
      onFilePicked (event) {
        console.log('File at picked ' + this.fileUpload)
      },
    },
  }
</script>

<style lang='scss' scoped>
  // .search{
  //   margin-left: 20%
  // }
  td {
    border:none !important
  }
</style>
