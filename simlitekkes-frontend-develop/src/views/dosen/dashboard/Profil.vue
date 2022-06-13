<template>
  <v-container
    style="width:90%"
    fluid
  >
    <v-row>
      <v-col
        cols="12"
        md="2"
      >
        <v-row />
        <v-row
          align="start"
          justify="start"
        >
          <v-img
            :src="imageSrc"
            height="250"
            contain
            class="grey darken-4"
          />
        </v-row>
        <v-row v-if="isUbah">
          <v-file-input
            v-model="fileFoto"
            accept="image/png, image/jpeg, image/bmp"
            prepend-icon="mdi-camera"
            label="Foto"
            @change="onFilePicked"
          />
        </v-row>
      </v-col>
      <v-col
        cols="12"
        md="5"
      >
        <v-simple-table>
          <template v-slot:default>
            <tbody>
              <tr>
                <td>NIDN/NIDK</td>
                <td v-if="!isUbah">
                  {{ dosen.nidn }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="$v.dosen.nidn.$model"
                    type="number"
                    counter="100"
                    :disabled="true"
                    :error-messages="$v.dosen.nidn.$error ? ['Isian tidak boleh lebih dari 100 karakter'] : []"
                  />
                </td>
              </tr>
              <tr>
                <td>Institusi</td>
                <td v-if="!isUbah">
                  {{ dosen.namaInstitusi }}
                </td>
                <td v-if="isUbah">
                  <v-select
                    v-model="dosen.idOrganisasi"
                    :items="listOrganisasi"
                    type="text"
                    v-if="dosen.nidn !== null"
                    :disabled="dosen.nidn !== null"
                  />
                </td>
              </tr>
              <tr>
                <td>Program Studi</td>
                <td v-if="!isUbah">
                  {{ dosen.programStudi }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="dosen.programStudi"
                    :disabled="dosen.nidn !== null"
                  />
                </td>
              </tr>
              <tr>
                <td>Jenjang Pendidikan</td>
                <td v-if="!isUbah">
                  {{ dosen.jenjangPendidikanTerakhir }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="dosen.jenjangPendidikanTerakhir"
                    :disabled="dosen.nidn !== null"
                  />
                </td>
              </tr>
              <tr>
                <td>Nama Lengkap</td>
                <td v-if="!isUbah">
                  {{ dosen.nama }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="dosen.nama"
                    counter="100"
                    :disabled="dosen.nidn !== null"
                    :error-messages="!$v.dosen.nama.maxLength ? ['Isian tidak boleh lebih dari 100 karakter'] : []"
                  />
                </td>
              </tr>
              <tr>
                <td>Status Aktif</td>
                <td v-if="!isUbah">
                  {{ dosen.isActive === true ? 'Ya' : (dosen.isActive === false) ? 'Tidak' : '-' }}
                </td>
                <td v-if="isUbah">
                  <v-radio-group
                    v-model="dosen.isActive"
                    row
                    style="font-size: small"
                    :disabled="dosen.nidn !== null"
                  >
                    <v-radio
                      v-for="kel in listAktif"
                      :key="kel.value"
                      :value="kel.value"
                      :label="kel.text"
                    />
                  </v-radio-group>
                </td>
              </tr>
              <tr>
                <td>Jabatan Akademik</td>
                <td v-if="!isUbah">
                  {{ dosen.jabatanAkademikFungsional }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="dosen.jabatanAkademikFungsional"
                    :disabled="dosen.nidn !== null"
                  />
                </td>
              </tr>
              <tr>
                <td>Beban Review Maksimal</td>
                <td v-if="!isUbah">
                  {{ dosen.bebanReviewMaksimal }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="dosen.bebanReviewMaksimal"
                    type="number"
                    counter="2"
                    :error-messages="!$v.dosen.bebanReviewMaksimal.maxLength ? ['Isian tidak boleh lebih dari 2 karakter'] : []"
                  />
                </td>
              </tr>
              <tr>
                <td>Gelar Akademik Depan</td>
                <td v-if="!isUbah">
                  {{ dosen.gelarDepan }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="dosen.gelarDepan"
                    counter="100"
                    :error-messages="!$v.dosen.gelarDepan.maxLength ? ['Isian tidak boleh lebih dari 100 karakter'] : []"
                  />
                </td>
              </tr>
              <tr>
                <td>Gelar Akademik Belakang</td>
                <td v-if="!isUbah">
                  {{ dosen.gelarBelakang }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="dosen.gelarBelakang"
                    counter="100"
                    :error-messages="!$v.dosen.gelarDepan.maxLength ? ['Isian tidak boleh lebih dari 100 karakter'] : []"
                  />
                </td>
              </tr>
              <tr>
                <td>Bidang Keahlian</td>
                <td v-if="!isUbah">
                  {{ dosen.namaBidang }}
                </td>
                <td v-if="isUbah">
                  <v-select
                    v-model="dosen.idBidangKeahlian"
                    :items="listBidang"
                    type="text"
                  />
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
      <v-col
        cols="12"
        md="5"
      >
        <v-simple-table>
          <template v-slot:default>
            <tbody>
              <tr>
                <td>Jenis Kelamin</td>
                <td v-if="!isUbah">
                  {{ dosen.jenisKelaminStr }}
                </td>
                <td v-if="isUbah">
                  <v-radio-group
                    v-model="dosen.jenisKelamin"
                    row
                    style="font-size: small"
                  >
                    <v-radio
                      v-for="kel in listKelamin"
                      :key="kel.value"
                      :value="kel.value"
                      :label="kel.text"
                    />
                  </v-radio-group>
                </td>
              </tr>
              <tr>
                <td>Tempat/Tanggal Lahir</td>
                <td v-if="!isUbah">
                  {{ dosen.tempatLahir }} / {{ dosen.dateOfBirthFormattedDtp }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="dosen.tempatLahir"
                    prepend-icon="mdi-home"
                    counter="100"
                    :error-messages="!$v.dosen.tempatLahir.maxLength ? ['Isian tidak boleh lebih dari 100 karakter'] : []"
                  />
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
                        v-model="computedTglLahir"
                        prepend-icon="mdi-calendar-range"
                        readonly
                        v-on="on"
                      />
                    </template>
                    <v-date-picker
                      ref="picker"
                      v-model="dosen.tanggalLahir"
                      :max="new Date().toISOString().substr(0, 10)"
                      min="1900-01-01"
                      @change="save"
                    />
                  </v-menu>
                </td>
              </tr>
              <tr>
                <td>No. KTP</td>
                <td v-if="!isUbah">
                  {{ dosen.noKtp }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="$v.dosen.noKtp.$model"
                    counter="16"
                    type="number"
                    :error-messages="$v.dosen.noKtp.$error ? ['Isian harus 16 karakter'] : []"
                  />
                </td>
              </tr>
              <tr>
                <td>No. Telepon</td>
                <td v-if="!isUbah">
                  {{ dosen.noTelp }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="$v.dosen.noTelp.$model"
                    type="number"
                    counter="20"
                    :error-messages="$v.dosen.noTelp.$error ? ['Isian tidak boleh lebih dari 20 karakter'] : []"
                  />
                </td>
              </tr>
              <tr>
                <td>No. HP</td>
                <td v-if="!isUbah">
                  {{ dosen.noHp }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="$v.dosen.noHp.$model"
                    type="number"
                    counter="20"
                    :error-messages="$v.dosen.noHp.$error ? ['Isian tidak boleh lebih dari 20 karakter'] : []"
                  />
                </td>
              </tr>
              <tr>
                <td>Email</td>
                <td v-if="!isUbah">
                  {{ dosen.email }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="$v.dosen.email.$model"
                    counter="100"
                    :error-messages="$v.dosen.email.$error ? ['Format tidak sesuai'] : []"
                  />
                </td>
              </tr>
              <tr>
                <td>Website Personal</td>
                <td v-if="!isUbah">
                  {{ dosen.websitePersonal }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="dosen.websitePersonal"
                    counter="100"
                    :error-messages="!$v.dosen.websitePersonal.maxLength ? ['Isian tidak boleh lebih dari 100 karakter'] : []"
                  />
                </td>
              </tr>
              <tr>
                <td>Alamat</td>
                <td v-if="!isUbah">
                  {{ dosen.alamat }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="dosen.alamat"
                    counter="100"
                    :error-messages="!$v.dosen.alamat.maxLength ? ['Isian tidak boleh lebih dari 100 karakter'] : []"
                  />
                </td>
              </tr>
              <tr>
                <td>Terlatih Sebagai Reviewer</td>
                <td v-if="!isUbah">
                  {{ dosen.isTrained === true ? 'Ya' : (dosen.isTrained === false) ? 'Tidak' : '-' }}
                </td>
                <td v-if="isUbah">
                  <v-radio-group
                    v-model="dosen.isTrained"
                    row
                    style="font-size: small"
                  >
                    <v-radio
                      v-for="kel in listTerlatih"
                      :key="kel.value"
                      :value="kel.value"
                      :label="kel.text"
                    />
                  </v-radio-group>
                </td>
              </tr>
              <tr>
                <td>Nomor Sertifikasi</td>
                <td v-if="!isUbah">
                  {{ dosen.nomorSertifikasi }}
                </td>
                <td v-if="isUbah">
                  <v-text-field
                    v-model="dosen.nomorSertifikasi"
                    counter="20"
                    :error-messages="!$v.dosen.nomorSertifikasi.maxLength ? ['Isian tidak boleh lebih dari 20 karakter'] : []"
                  />
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>
    <v-row class="mt-6">
      <v-col class="text-right mt-6">
        <v-dialog
          v-model="dialog"
          max-width="600px"
        >
          <template v-slot:activator="{ on }">
            <v-col class="text-right">
              <v-card-actions>
                <div class="flex-grow-1" />
                <v-row class="mt-1">
                  <v-col class="align text-right mt-1">
                    <v-btn
                      color="blue"
                      class="mb-2 white--text mr-2"
                      @click="getApiDosen()"
                      v-on="on"
                      v-if="isUbah"
                    >
                      Sinkronisasi PDDIKTI
                    </v-btn>
                    <v-btn
                      v-if="!isUbah"
                      color="green"
                      class="mb-2 white--text mr-2"
                      @click="updateHalaman()"
                    >
                      Ubah
                    </v-btn>
                    <v-btn
                      v-if="isUbah"
                      color="green"
                      class="mb-2 white--text mr-2"
                      @click="updateData()"
                    >
                      Simpan
                    </v-btn>
                  </v-col>
                </v-row>
              </v-card-actions>
            </v-col>
          </template>
        </v-dialog>
      </v-col>
    </v-row>
    <v-snackbar
      v-model="snackbar"
      :color="snackbarColor"
      :timeout="7000"
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
  import { email, maxLength, minLength } from 'vuelidate/lib/validators'

  export default {
    data: () => ({
      dialog: false,
      dosen: {
        username: '',
      },
      isUbah: false,
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      menu: false,
      date: null,
      errors: false,
      validations: false,
      listBidang: [],
      listKelamin: [
        { value: 'P', text: 'Pria' },
        { value: 'W', text: 'Wanita' },
      ],
      tab: null,
      listOrganisasi: [],
      fileFoto: null,
      imageSrc: '',
      encodedFoto: '',
      listTerlatih: [
        { value: true, text: 'Ya' },
        { value: false, text: 'Tidak' },
      ],
      listAktif: [
        { value: true, text: 'Aktif' },
        { value: false, text: 'Tidak Aktif' },
      ],
      countfail: 0,
    }),

    computed: {
      computedTglLahir () {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.dosen.dateOfBirthFormattedDtp = this.dosen.tanggalLahir
        return this.dosen.dateOfBirthFormattedDtp
          ? moment(this.dosen.dateOfBirthFormattedDtp).format('DD-MM-YYYY')
          : ''
      },
    },

    watch: {
      menu (val) {
        val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
      },
    },

    created () {
      this.getData()
    },

    validations: {
      dosen: {
        email: {
          email,
          maxLength: maxLength(100),
        },
        noHp: { maxLength: maxLength(20) },
        noTelp: { maxLength: maxLength(20) },
        noKtp: { minLength: minLength(16), maxLength: maxLength(16) },
        bebanReviewMaksimal: { maxLength: maxLength(2) },
        gelarDepan: { maxLength: maxLength(100) },
        gelarBelakang: { maxLength: maxLength(100) },
        tempatLahir: { maxLength: maxLength(100) },
        websitePersonal: { maxLength: maxLength(100) },
        alamat: { maxLength: maxLength(100) },
        nama: { maxLength: maxLength(100) },
        nidn: { maxLength: maxLength(100) },
        nomorSertifikasi: { maxLength: maxLength(20) },
      },
    },

    methods: {
      save (date) {
        this.$refs.menu.save(date)
      },
      getData () {
        axios.all([
          apiServer.get('/peneliti/user/' + this.$store.state.user.userId),
          apiServer.get('/bidang-keahlian/all'),
          apiServer.get('/organisasi'),
        ]).then(axios.spread((detailDosen, listBidang, listOrganisasi) => {
          this.dosen = detailDosen.data
          this.dosen.username = this.$store.state.user.name
          this.formattingDate(this.dosen)
          listBidang.data.forEach(this.populateBidang)
          listOrganisasi.data.forEach(this.populateOrganisasi)
          if (this.dosen.foto != null) {
            this.getFoto()
          }
        })).catch(error => {
          console.log('There was an error:', error.response) // Logs out the error
        })
      },
      getFoto () {
        apiServer.get('/userweb-foto/' + this.$store.state.user.userId).then(response => {
          this.encodedFoto = response.data
          this.fileFoto = this.dataURLtoFile(
            this.encodedFoto,
            this.dosen.fotoName
          )
          const fileReader = new FileReader()
          fileReader.addEventListener('load', () => {
            this.imageSrc = fileReader.result
          })
          fileReader.readAsDataURL(this.fileFoto)
        })
      },
      updateHalaman () {
        if (this.dosen.id === null) {
          this.showSnackbar('danger', 'Harap menghubungi operator untuk memperbarui data peneliti')
        } else {
          this.isUbah = true
          this.dialog = false
        }
      },
      viewHalaman () {
        this.isUbah = false
        this.getData()
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
      updateData () {
        this.errors = this.$v.dosen.$anyError
        this.validations = this.$v.dosen.$invalid
        if (this.errors === true || this.validations === true) {
          return
        }
        apiServer
          .put('/peneliti', this.dosen)
          .then(response => {
            if (this.fileFoto != null && (this.fileFoto.name !== this.dosen.fotoName)) {
              console.log('post foto')
              if (this.dosen.fotoName != null) {
                this.deleteFoto(this.$store.state.user.userId)
              }
              const formData = new FormData()
              formData.append('file', this.fileFoto)
              apiServer
                .post('/userweb-foto/' + this.$store.state.user.userId, formData)
                .then(response => {
                  this.getData()
                  this.showSnackbar('success', 'Data berhasil diubah')
                  this.isUbah = false
                  this.dialog = false
                })
                .catch(error => {
                  console.log('There was an error:', error.response)
                  this.showSnackbar(
                    'error',
                    'Foto gagal disimpan'
                  )
                })
            } else {
              this.getData()
              this.showSnackbar('success', 'Data berhasil diubah')
              this.isUbah = false
              this.dialog = false
            }
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Terdapat kesalahan input')
            this.dialog = false
          })
      },
      formattingDate (value) {
        value.dateOfBirthFormattedDtp = value.tanggalLahir
          ? moment(value.tanggalLahir).format('DD-MM-YYYY')
          : ''
        value.tanggalLahir = value.tanggalLahir
          ? moment(value.tanggalLahir).format('YYYY-MM-DD')
          : ''
      },
      populateBidang (value) {
        this.listBidang.push({ value: value.id, text: value.nama })
      },
      populateOrganisasi (value) {
        this.listOrganisasi.push({ value: value.id, text: value.nama })
      },
      onFilePicked (event) {
        console.log('fileFoto at picked ', this.fileFoto)
        const fileReader = new FileReader()
        fileReader.addEventListener('load', () => {
          this.imageSrc = fileReader.result
        })
        fileReader.readAsDataURL(event)
        console.log('DEBUG : ' + this.imageSrc)
      },
      dataURLtoFile (dataurl, filename) {
        // console.log("DATA URL " + dataurl);
        var arr = dataurl.split(',')
        var mime = arr[0].match(/:(.*?);/)[1]
        var bstr = atob(arr[1])
        var n = bstr.length
        var u8arr = new Uint8Array(n)

        while (n--) {
          u8arr[n] = bstr.charCodeAt(n)
        }

        return new File([u8arr], filename, { type: mime })
      },
      deleteFoto (id) {
        apiServer
          .delete('/userweb-foto/' + id)
          .then(response => {
            console.log('old file has been deleted')
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      loginDikti () {
        apiServer
          .get('/peneliti/dikti/login')
          .then(response => {
            this.$store.dispatch('setTokenPddikti', response.data)
            this.getApiDosen()
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.dialog = false
            this.showSnackbar('danger', 'Koneksi ke dikti sedang mengalami gangguan, coba lagi beberapa saat')
          })
      },
      getApiDosen () {
        if (this.$store.state.tokenDikti) {
          apiServer
            .get('/peneliti/dikti/' + this.dosen.nidn + '/' + this.$store.state.tokenDikti)
            .then(response => {
              if (response.data.status === '401' || response.data.status === '500') {
                this.countFail++
                if (this.countFail === 5) {
                  this.showSnackbar('error', 'Sinkronisasi gagal, coba lagi beberapa saat')
                  this.countFail = 0
                } else if (!this.$store.state.tokenDikti || (this.$store.state.tokenDikti && response.data.status !== '200')) {
                  this.loginDikti()
                }
              } else if (response.data.status === '404') {
                this.countFail = 0
                this.showSnackbar('error', 'NIDN tidak terdaftar di PDDIKTI')
                this.dialog = false
              } else {
                this.dosenApi = response.data
                this.countFail = 0
                apiServer
                  .get('/prodi/dikti/' + this.$store.state.tokenDikti + '/' + this.dosenApi.data[0].kode_pt + '/' + this.dosenApi.data[0].kode_prodi)
                  .then(response => {
                    var ptDetail = response.data
                    if (ptDetail.status === '200') this.dosen.programStudi = ptDetail.data[0].nama
                    this.toDosen(this.dosenApi)
                    this.showSnackbar('success', 'Pengambilan data berhasil, klik simpan untuk menyimpan perubahan')
                    this.dialog = false
                  })
                  .catch(error => {
                    console.log('fetch pt api error:', error.response) // Logs out the error
                  })
              }
            })
            .catch(error => {
              console.log('There was an error fetch api:', error.response) // Logs out the error
              this.showSnackbar('error', 'Terdapat gangguan koneksi ke server tujuan, coba lagi beberapa saat')
              this.dialog = false
            })
        } else {
          this.loginDikti()
        }
      },
      toDosen (item) {
        var dosenApi = item.data[0]
        this.dosen.nidn = dosenApi.nidn
        this.dosen.nama = dosenApi.nama
        this.dosen.jenisKelamin = dosenApi.jenis_kelamin
        this.dosen.gelarDepan = dosenApi.gelar_depan
        this.dosen.gelarBelakang = dosenApi.gelar_belakang
        this.dosen.jenjangPendidikanTerakhir = dosenApi.pendidikan_terakhir
        this.dosen.jabatanAkademikFungsional = dosenApi.jabatan_fungsional.nama
        this.dosen.email = dosenApi.email
        this.dosen.tempatLahir = dosenApi.tempat_lahir
        if (dosenApi.tgl_lahir) {
          this.dosen.tanggalLahir = new Date(dosenApi.tgl_lahir)
          this.formattingDate(this.dosen)
        }
        this.dosen.noHp = dosenApi.handphone
        this.dosen.alamat = dosenApi.alamat.jalan
        this.dosen.lastSyncTime = new Date()
        this.dosen.isActive = dosenApi.status_keaktifan.nama === 'Aktif'
        this.dosen.kodePddikti = dosenApi.kode_pt
      },
    },
  }
</script>

<style lang="scss" scoped>
  // .search{
  //   margin-left: 20%;
  // }
  #tblPokok td {
    border: 1px dotted black;
  }

</style>
