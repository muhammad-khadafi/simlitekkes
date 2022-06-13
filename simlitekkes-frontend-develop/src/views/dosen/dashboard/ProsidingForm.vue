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
                  v-model="prosiding.output.judul"
                  :error-messages="$v.prosiding.output.judul.$error ? ['Isian harus diisi'] : []"
                  :auto-grow="true"
                  rows="1"
                  counter="255"
                  :disabled="isUbah"
                />
              </td>
            </tr>
            <tr>
              <td>Jenis Prosiding*</td>
              <td>
                <v-select
                  v-model="prosiding.output.idJenisOutput"
                  :items="listJenisOutput"
                  type="text"
                  :error-messages="$v.prosiding.output.idJenisOutput.$error ? ['Pilihan harus dipilih'] : []"
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
                      v-on="on"
                      :error-messages="$v.prosiding.output.tanggalPublikasi.$error ? ['Tanggal harus diisi'] : []"
                    />
                  </template>
                  <v-date-picker
                    ref="picker"
                    v-model="prosiding.output.tanggalPublikasi"
                    :max="new Date().toISOString().substr(0, 10)"
                    min="1900-01-01"
                    @change="save"
                  />
                </v-menu>
              </td>
            </tr>
            <tr>
              <td>Peran Penulis*</td>
              <td>
                <v-select
                  v-model="prosiding.penulisOutput.idPeranPenulisOutput"
                  :items="listPeran"
                  type="text"
                  :error-messages="$v.prosiding.penulisOutput.idPeranPenulisOutput.$error ? ['Pilih salah satu'] : []"
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
              <td>Nama Prosiding*</td>
              <td>
                <v-text-field
                  v-model="prosiding.namaProsiding"
                  type="text"
                  :error-messages="$v.prosiding.namaProsiding.$error ? ['Isian harus diisi'] : []"
                  counter="100"
                />
              </td>
            </tr>
            <tr>
              <td>URL*</td>
              <td>
                <v-text-field
                  v-model="prosiding.output.url"
                  type="text"
                  counter="100"
                  :error-messages="$v.prosiding.output.url.$error ? ['Isian harus diisi'] : []"
                />
              </td>
            </tr>
            <tr>
              <td>Berkas Prosiding <br>(pdf maks 5 MB)*</td>
              <td>
                <v-file-input
                  v-model="fileUpload"
                  @change="onFilePicked"
                  label="Upload File"
                  accept="application/pdf"
                  ref="fileInput"
                  :rules="rules"
                  required
                  @input="$v.fileUpload.$touch()"
                  @blur="$v.fileUpload.$touch()"
                  :error-messages="fileUploadErrors"
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
      <v-col class="text-right mt-6">
        <v-btn
          color="green"
          class="mb-2 white--text mr-2"
          @click="saveForm()"
        >
          Simpan
        </v-btn>
        <v-btn
          to="/prosiding-dosen"
          color="red"
          class="mb-2 white--text mr-2"
        >
          Batal
        </v-btn>
      </v-col>
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
      prosiding: {
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
        this.prosiding.tahunProsiding = new Date(this.prosiding.output.tanggalPublikasi).getFullYear()
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.prosiding.dateOfBirthFormattedDtp = this.prosiding.output.tanggalPublikasi
        return this.prosiding.dateOfBirthFormattedDtp
          ? moment(this.prosiding.dateOfBirthFormattedDtp).format('DD-MM-YYYY')
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
      prosiding: {
        namaProsiding: { required },
        output: {
          judul: { required },
          tanggalPublikasi: { required },
          idJenisOutput: { required },
          url: { required },
        },
        penulisOutput: {
          idPeranPenulisOutput: { required },
        },
      },
      fileUpload: { required },
    },

    methods: {
      save (date) {
        this.$refs.menu.save(date)
      },
      getData () {
        this.isUbah ? this.getProsiding() : this.hasAccess = true
        axios.all([
          apiServer.get('/peran-penulis-output/all'),
          apiServer.get('/jenis-output/prosiding'),
          apiServer.get('/peneliti/user/' + this.$store.state.user.userId),
        ]).then(axios.spread((listPeran, listJenisOutput, peneliti) => {
          listPeran.data.forEach(this.populatePeran)
          listJenisOutput.data.forEach(this.populateJenisOutput)
          this.peneliti = peneliti.data
        })).catch(error => {
          console.log('Terdapat error:', error.response) // Logs out the error
        })
      },
      getProsiding () {
        axios.all([
          apiServer.get('/prosiding/detail/' + this.$route.params.id),
          apiServer.get('/output-file/' + this.$route.params.id),
        ]).then(axios.spread((response, response1) => {
          this.prosiding = response.data
          this.hasAccess = this.prosiding.idPenelitian === null || (this.prosiding.idPenelitian != null && this.prosiding.idPeranPeneliti === 1)
          this.formattingDate(this.prosiding)
          this.fileUpload = CommonService.dataURLtoFile(
            response1.data,
            this.prosiding.fileName
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
        this.prosiding.output.timestampUpload = new Date()
        const formData = new FormData()
        formData.append('file', this.fileUpload)
        apiServer
          .post('/output-upload/prosiding', formData)
          .then(response => {
            this.prosiding.output.pathFile = response.data.filePath
            apiServer
              .post('/output', this.prosiding.output)
              .then(response => {
                this.prosiding.penulisOutput.idOutput = response.data
                this.prosiding.penulisOutput.idPeneliti = this.peneliti.id
                this.prosiding.idOutput = response.data
                this.prosiding.penulisOutput.username = this.$store.state.user.name
                this.prosiding.username = this.$store.state.user.name
                axios.all([
                  apiServer.post('/prosiding', this.prosiding),
                  apiServer.post('/penulis-output', this.prosiding.penulisOutput),
                ]).then(axios.spread((response1, response2) => {
                  this.showSnackbar('success', 'Data berhasil ditambah')
                  this.$router.replace('/prosiding-dosen')
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
              })
          })
          .catch(error => {
            console.log('There was an error:', error.response)
          })
      },
      updateData () {
        if (this.fileUpload.name !== this.prosiding.fileName) {
          const formData = new FormData()
          formData.append('file', this.fileUpload)
          axios.all([
            apiServer.post('/output-upload/prosiding', formData),
            apiServer.delete('/output-file/' + this.prosiding.idOutput),
          ]).then(axios.spread((response1, response2) => {
            this.prosiding.output.pathFile = response1.data.filePath
            this.prosiding.output.timestampUpload = new Date()
            this.updateRowData()
          })).catch(error => {
            console.log('There was an error:', error.response1)
            console.log('There was an error:', error.response2)
          })
        } else {
          this.updateRowData()
        }
      },
      updateRowData () {
        axios.all([
          apiServer.put('/prosiding', this.prosiding),
          apiServer.put('/penulis-output', this.prosiding.penulisOutput),
          apiServer.put('/output', this.prosiding.output),
        ]).then(axios.spread((response1, response2, response3) => {
          this.showSnackbar('success', 'Data berhasil ditambah')
          this.$router.replace('/prosiding-dosen')
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
          })
      },
      validateAll () {
        this.$v.$touch()
        this.errors = this.$v.prosiding.$anyError || this.fileError || this.$v.prosiding.$invalid
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
