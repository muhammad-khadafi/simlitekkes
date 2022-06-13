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
                  v-model="buku.output.judul"
                  :error-messages="$v.buku.output.judul.$error ? ['Isian harus diisi'] : []"
                  :auto-grow="true"
                  rows="1"
                  counter="255"
                  :disabled="isUbah"
                />
              </td>
            </tr>
            <tr>
              <td>Jenis Buku*</td>
              <td>
                <v-select
                  v-model="buku.output.idJenisOutput"
                  :items="listJenisOutput"
                  type="text"
                  :error-messages="$v.buku.output.idJenisOutput.$error ? ['Pilihan harus dipilih'] : []"
                />
              </td>
            </tr>
            <tr>
              <td>Nama Penerbit*</td>
              <td>
                <v-text-field
                  v-model="buku.namaPenerbit"
                  type="text"
                  :error-messages="$v.buku.namaPenerbit.$error ? ['Isian harus diisi'] : []"
                  counter="100"
                />
              </td>
            </tr>
            <tr>
              <td>Tanggal Terbit*</td>
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
                      :error-messages="$v.buku.output.tanggalPublikasi.$error ? ['Tanggal harus diisi'] : []"
                    />
                  </template>
                  <v-date-picker
                    ref="picker"
                    v-model="buku.output.tanggalPublikasi"
                    :max="new Date().toISOString().substr(0, 10)"
                    min="1900-01-01"
                    @change="save"
                  />
                </v-menu>
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
              <td>ISBN*</td>
              <td>
                <v-text-field
                  v-model="buku.isbn"
                  type="text"
                  :error-messages="$v.buku.isbn.$error ? ['Isian harus diisi'] : []"
                  counter="25"
                />
              </td>
            </tr>
            <tr>
              <td>Jumlah Halaman*</td>
              <td>
                <v-text-field
                  v-model="buku.jumlahHalaman"
                  type="number"
                  :error-messages="$v.buku.jumlahHalaman.$error ? ['Isian harus diisi'] : []"
                />
              </td>
            </tr>
            <tr>
              <td>URL*</td>
              <td>
                <v-text-field
                  v-model="buku.output.url"
                  type="text"
                  counter="100"
                  :error-messages="$v.buku.output.url.$error ? ['Isian harus diisi'] : []"
                />
              </td>
            </tr>
            <tr>
              <td>Berkas Buku <br>(pdf maks 5 MB)*</td>
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
          to="/buku-dosen"
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
      buku: {
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
        this.buku.tahunTerbit = new Date(this.buku.output.tanggalPublikasi).getFullYear()
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.buku.dateOfBirthFormattedDtp = this.buku.output.tanggalPublikasi
        return this.buku.dateOfBirthFormattedDtp
          ? moment(this.buku.dateOfBirthFormattedDtp).format('DD-MM-YYYY')
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
      buku: {
        namaPenerbit: { required },
        isbn: { required },
        jumlahHalaman: { required },
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
        this.isUbah ? this.getBuku() : this.hasAccess = true
        axios.all([
          apiServer.get('/peran-penulis-output/all'),
          apiServer.get('/jenis-output/buku'),
          apiServer.get('/peneliti/user/' + this.$store.state.user.userId),
        ]).then(axios.spread((listPeran, listJenisOutput, peneliti) => {
          listPeran.data.forEach(this.populatePeran)
          listJenisOutput.data.forEach(this.populateJenisOutput)
          this.peneliti = peneliti.data
        })).catch(error => {
          console.log('Terdapat error:', error.response) // Logs out the error
        })
      },
      getBuku () {
        axios.all([
          apiServer.get('/buku/detail/' + this.$route.params.id),
          apiServer.get('/output-file/' + this.$route.params.id),
        ]).then(axios.spread((response, response1) => {
          this.buku = response.data
          this.hasAccess = this.buku.idPenelitian === null || (this.buku.idPenelitian != null && this.buku.idPeranPeneliti === 1)
          this.formattingDate(this.buku)
          this.fileUpload = CommonService.dataURLtoFile(
            response1.data,
            this.buku.fileName
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
        this.buku.output.timestampUpload = new Date()
        const formData = new FormData()
        formData.append('file', this.fileUpload)
        apiServer
          .post('/output-upload/buku', formData)
          .then(response => {
            this.buku.output.pathFile = response.data.filePath
            apiServer
              .post('/output', this.buku.output)
              .then(response => {
                this.buku.penulisOutput.idOutput = response.data
                this.buku.penulisOutput.idPeneliti = this.peneliti.id
                this.buku.penulisOutput.username = this.$store.state.user.name
                this.buku.idOutput = response.data
                this.buku.username = this.$store.state.user.name
                axios.all([
                  apiServer.post('/buku', this.buku),
                  apiServer.post('/penulis-output', this.buku.penulisOutput),
                ]).then(axios.spread((response1, response2) => {
                  this.showSnackbar('success', 'Data berhasil ditambah')
                  this.$router.replace('/buku-dosen')
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
        if (this.fileUpload.name !== this.buku.fileName) {
          const formData = new FormData()
          formData.append('file', this.fileUpload)
          axios.all([
            apiServer.post('/output-upload/buku', formData),
            apiServer.delete('/output-file/' + this.buku.idOutput),
          ]).then(axios.spread((response1, response2) => {
            this.buku.output.pathFile = response1.data.filePath
            this.buku.output.timestampUpload = new Date()
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
          apiServer.put('/buku', this.buku),
          apiServer.put('/penulis-output', this.buku.penulisOutput),
          apiServer.put('/output', this.buku.output),
        ]).then(axios.spread((response1, response2, response3) => {
          this.showSnackbar('success', 'Data berhasil ditambah')
          this.$router.replace('/buku-dosen')
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
        this.errors = this.$v.buku.$anyError || this.fileError || this.$v.buku.$invalid
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
