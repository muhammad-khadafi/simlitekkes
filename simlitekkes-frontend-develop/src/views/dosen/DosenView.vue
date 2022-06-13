<template>
  <v-container>
    <material-card
      v-if="hasAccess == false || isExist === false"
      class="mb-6"
    >
      <v-row>
        <v-col>
          <page403 />
        </v-col>
      </v-row>
    </material-card>
    <material-card
      v-if="hasAccess && isExist"
      class="mb-6"
    >
      <v-card-title primary-title>
        <div>
          <h2 class="subtitle mb-1">
            {{ isUbah ? 'Ubah Peneliti' : 'Tambah Peneliti' }}
          </h2>
        </div>
      </v-card-title>
      <v-divider />
      <v-app id="inspire">
        <v-row v-if="snackbar">
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
        </v-row>
        <v-row>
          <v-col
            cols="12"
            md="2"
          />
          <v-col
            cols="12"
            md="3"
            class="mt-6"
          >
            <v-text-field
              v-model="nidnSearch"
              label="NIDN"
              :error-messages="isNidnUsed ? ['NIDN sudah digunakan, silahkan ketikkan NIDN lain'] : []"
            />
          </v-col>
          <v-col
            cols="12"
            md="2"
          >
            <v-dialog
              v-model="dialog"
              max-width="600px"
            >
              <template v-slot:activator="{ on }">
                <v-col class="text-left">
                  <v-card-actions>
                    <div class="flex-grow-1" />
                    <v-row class="mt-1">
                      <v-col class="align text-left mt-1">
                        <v-btn
                          color="blue"
                          class="mb-2 white--text mr-2"
                          @click="getApiDosen()"
                          v-on="on"
                        >
                          Sinkronisasi PDDIKTI
                        </v-btn>
                      </v-col>
                    </v-row>
                  </v-card-actions>
                </v-col>
              </template>
            </v-dialog>
          </v-col>
        </v-row>

        <material-card
          color="blue"
          title="Data Pokok"
          class="font-weight-bold"
        >
          <v-container style="width:85%">
            <v-row>
              <v-col
                cols="12"
                md="6"
              >
                <v-simple-table>
                  <template v-slot:default>
                    <tbody>
                      <tr>
                        <td>NIDN</td>
                        <td>
                          <v-text-field
                            v-model="dosen.nidn"
                            counter="100"
                            :error-messages="!$v.dosen.nidn.maxLength ? ['Isian tidak boleh lebih dari 100 karakter'] : []"
                            :disabled="true"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Nama Lengkap</td>
                        <td>
                          <v-text-field
                            v-model="dosen.nama"
                            counter="100"
                            :disabled="dosen.nidn !== null"
                            :error-messages="!$v.dosen.nama.maxLength ? ['Isian tidak boleh lebih dari 100 karakter'] : []"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Institusi</td>
                        <td>
                          <v-select
                            v-model="dosen.idOrganisasi"
                            :items="listOrganisasi"
                            type="text"
                            :disabled="dosen.nidn !== null || isPpm"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Program Studi</td>
                        <td>
                          <v-text-field
                            v-model="dosen.programStudi"
                            :disabled="dosen.nidn !== null"
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
                        <td>Jenjang Pendidikan</td>
                        <td>
                          <v-text-field
                            v-model="dosen.jenjangPendidikanTerakhir"
                            :disabled="dosen.nidn !== null"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Jabatan Akademik</td>
                        <td>
                          <v-text-field
                            v-model="dosen.jabatanAkademikFungsional"
                            :disabled="dosen.nidn !== null"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Status Aktif</td>
                        <td>
                          <v-radio-group
                            v-model="dosen.isActive"
                            row
                            :disabled="dosen.nidn !== null"
                            style="font-size: small"
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
                    </tbody>
                  </template>
                </v-simple-table>
              </v-col>
            </v-row>
          </v-container>
        </material-card>

        <material-card
          color="blue"
          title="Data Non Pokok"
          class="font-weight-bold"
        >
          <v-container style="width:85%">
            <v-row>
              <v-col
                cols="12"
                md="6"
              >
                <v-simple-table>
                  <template v-slot:default>
                    <tbody>
                      <tr>
                        <td>Bidang Keahlian</td>
                        <td>
                          <v-select
                            v-model="dosen.idBidangKeahlian"
                            :items="listBidang"
                            type="text"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Beban Review Maksimal</td>
                        <td>
                          <v-text-field
                            v-model="dosen.bebanReviewMaksimal"
                            type="number"
                            counter="2"
                            :error-messages="bebanReviewErrors"
                            @input="$v.dosen.bebanReviewMaksimal.$touch()"
                            @blur="$v.dosen.bebanReviewMaksimal.$touch()"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Gelar Akademik Depan</td>
                        <td>
                          <v-text-field
                            v-model="dosen.gelarDepan"
                            counter="100"
                            :error-messages="!$v.dosen.gelarDepan.maxLength ? ['Isian tidak boleh lebih dari 100 karakter'] : []"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Gelar Akademik Belakang</td>
                        <td>
                          <v-text-field
                            v-model="dosen.gelarBelakang"
                            counter="100"
                            :error-messages="!$v.dosen.gelarBelakang.maxLength ? ['Isian tidak boleh lebih dari 100 karakter'] : []"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Tempat/Tanggal Lahir</td>
                        <td>
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
                        <td>
                          <v-text-field
                            v-model="$v.dosen.noKtp.$model"
                            type="number"
                            counter="16"
                            :error-messages="$v.dosen.noKtp.$error ? ['Isian harus 16 karakter'] : []"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Jenis Kelamin</td>
                        <td>
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
                        <td>No. Telepon</td>
                        <td>
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
                        <td>
                          <v-text-field
                            v-model="$v.dosen.noHp.$model"
                            type="number"
                            counter="20"
                            :error-messages="$v.dosen.noHp.$error ? ['Isian tidak boleh lebih dari 20 karakter'] : []"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Website Personal</td>
                        <td>
                          <v-text-field
                            v-model="dosen.websitePersonal"
                            counter="100"
                            :error-messages="!$v.dosen.websitePersonal.maxLength ? ['Isian tidak boleh lebih dari 100 karakter'] : []"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Email*</td>
                        <td>
                          <v-text-field
                            v-model="$v.dosen.email.$model"
                            counter="100"
                            :error-messages="emailErrors"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Alamat</td>
                        <td>
                          <v-text-field
                            v-model="dosen.alamat"
                            counter="100"
                            :error-messages="!$v.dosen.alamat.maxLength ? ['Isian tidak boleh lebih dari 100 karakter'] : []"
                          />
                        </td>
                      </tr>
                      <tr>
                        <td>Terlatih Sebagai Reviewer</td>
                        <td>
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
                        <td>
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
          </v-container>
        </material-card>
        <v-row class="mt-1">
          <v-col class="align text-right mt-1">
            <v-btn
              color="blue"
              class="mb-2 white--text mr-2"
              @click="updateData()"
            >
              Simpan
            </v-btn>
            <v-btn
              color="red"
              class="mb-2 white--text"
              @click="back()"
            >
              Kembali
            </v-btn>
          </v-col>
        </v-row>
      </v-app>
    </material-card>
  </v-container>
</template>

<script>
  import moment from 'moment'
  import axios from 'axios'
  import { apiServer } from '../../setup-axios'
  import { email, maxLength, minLength, between, required } from 'vuelidate/lib/validators'
  import MaterialCard from '../../components/material/Card'

  export default {
    components: { MaterialCard, Page403: () => import('@/views/403.vue') },
    data: () => ({
      dialog: false,
      dosen: {
        username: '',
      },
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      menu: false,
      date: null,
      errors: false,
      validations: false,
      listBidang: [],
      listKelamin: [
        { value: 'L', text: 'Laki-Laki' },
        { value: 'P', text: 'Perempuan' },
      ],
      listAktif: [
        { value: true, text: 'Aktif' },
        { value: false, text: 'Tidak Aktif' },
      ],
      listTerlatih: [
        { value: true, text: 'Ya' },
        { value: false, text: 'Tidak' },
      ],
      tokenDikti: null,
      dosenApi: null,
      countFail: 0,
      nidnSearch: null,
      isUbah: null,
      listOrganisasi: [],
      hasAccess: null,
      isPpm: null,
      isExist: null,
      organisasi: null,
      isNidnUsed: null,
    }),

    computed: {
      computedTglLahir () {
        this.dosen.dateOfBirthFormattedDtp = this.dosen.tanggalLahir
        return this.dosen.dateOfBirthFormattedDtp
          ? moment(this.dosen.dateOfBirthFormattedDtp).format('DD-MM-YYYY')
          : ''
      },
      bebanReviewErrors () {
        const errors = []
        if (!this.$v.dosen.bebanReviewMaksimal.$dirty) return errors
        !this.$v.dosen.bebanReviewMaksimal.maxLength && errors.push('Isian tidak boleh lebih dari 2 karakter')
        !this.$v.dosen.bebanReviewMaksimal.between && errors.push('Isian harus diantara 1 - 10')
        return errors
      },
      emailErrors () {
        const errorsForEmail = []
        if (!this.$v.dosen.email.$dirty) {
          return errorsForEmail
        }

        if (!this.$v.dosen.email.required) {
          errorsForEmail.push('Email harus diisi')
        }

        if (!this.$v.dosen.email.email) {
          errorsForEmail.push('Isi dengan format email yang valid')
        }

        if (!this.$v.dosen.email.maxLength) {
          errorsForEmail.push('Isian tidak boleh melebihi dari 100 karakter')
        }

        return errorsForEmail
      },
    },

    watch: {
      menu (val) {
        val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
      },
    },

    created () {
      this.isUbah = this.$route.params.id !== '0'
      var url
      if (this.$store.state.user.role.roleCode === 'PENGELOLA_PUSAT') {
        url = 'checkUserIsPengelolaPusat'
      } else if (this.$store.state.user.role.roleCode === 'KAPUS_PPM') {
        url = 'checkUserIsKapusPpm'
        this.isPpm = true
      } else if (this.$store.state.user.role.roleCode === 'OPERATOR_PPM') {
        url = 'checkUserIsOperatorPpm'
        this.isPpm = true
      } else {
        this.hasAccess = false
      }

      if (this.hasAccess === null) {
        apiServer
          .get('/validasi/' + url + '/' + this.$store.state.user.userId + '/' + this.$store.state.user.role.roleCode)
          .then(response => {
            if (this.isPpm && this.isUbah) {
              apiServer
                .get('/validasi/checkPenelitiIsSameOrganization/' + this.$route.params.id + '/' + this.$store.state.user.organizationId)
                .then(response1 => {
                  this.hasAccess = response.data && response1.data
                  if (this.hasAccess) {
                    this.getData()
                  }
                })
                .catch(error => {
                  console.log('error when checkValidasi: ', error.response)
                })
            } else {
              this.hasAccess = response.data
              if (this.hasAccess) {
                this.getData()
              }
            }
          })
          .catch(error => {
            console.log('error when checkValidasi: ', error.response)
          })
      }
    },

    validations: {
      dosen: {
        email: {
          email,
          maxLength: maxLength(100),
          required,
        },
        noHp: { maxLength: maxLength(20) },
        noTelp: { maxLength: maxLength(20) },
        noKtp: { minLength: minLength(16), maxLength: maxLength(16) },
        bebanReviewMaksimal: { maxLength: maxLength(2), between: between(1, 10) },
        gelarDepan: { maxLength: maxLength(100) },
        gelarBelakang: { maxLength: maxLength(100) },
        tempatLahir: { maxLength: maxLength(100) },
        websitePersonal: { maxLength: maxLength(100) },
        alamat: { maxLength: maxLength(100) },
        nomorSertifikasi: { maxLength: maxLength(20) },
        nama: { maxLength: maxLength(100) },
        nidn: { maxLength: maxLength(100) },
      },
    },

    methods: {
      save (date) {
        this.$refs.menu.save(date)
      },
      getData () {
        axios.all([
          apiServer.get('/peneliti/' + this.$route.params.id),
          apiServer.get('/bidang-keahlian/all'),
          apiServer.get('/organisasi/' + this.$store.state.user.organizationId),
        ]).then(axios.spread((detailDosen, listBidang, detailUserOrg) => {
          this.organisasi = detailUserOrg.data
          this.dosen = detailDosen.data
          this.isExist = (!this.isUbah || (this.isUbah && this.dosen.id !== null))
          if (this.dosen.nidn !== null) {
            this.nidnSearch = this.dosen.nidn
            this.getAllOrganisasi()
          } else {
            if (this.isPpm) {
              this.dosen.idOrganisasi = this.$store.state.user.organizationId
              this.getAllOrganisasi()
            } else {
              this.getAllOrganisasiNonPoltekkes()
            }
          }
          this.dosen.username = this.$store.state.user.name
          this.formattingDate(this.dosen)
          listBidang.data.forEach(this.populateBidang)
        })).catch(error => {
          console.log('There was an error (peneliti):', error.response) // Logs out the error
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
      updateData () {
        this.$v.$touch()
        this.errors = this.$v.dosen.$anyError
        this.validations = this.$v.dosen.$invalid
        if (this.errors === true || this.validations === true) {
          return
        }
        if (this.$route.params.id === '0') {
          apiServer
            .post('/peneliti', this.dosen)
            .then(response => {
              this.showSnackbar('success', 'Data berhasil ditambah')
              this.$router.replace('/dosen/' + response.data)
            })
            .catch(error => {
              console.log('There was an add error:', error.response)
            })
        } else {
          apiServer
            .put('/peneliti', this.dosen)
            .then(response => {
              this.showSnackbar('success', 'Data berhasil diubah')
            })
            .catch(error => {
              console.log('There was an update error:', error.response)
            })
        }
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
        apiServer
          .get('/validasi/checkNidnIsUsed/' + this.nidnSearch)
          .then(response => {
            this.isNidnUsed = response.data
            if (this.isNidnUsed === false) {
              if (this.$store.state.tokenDikti) {
                apiServer
                  .get('/peneliti/dikti/' + this.nidnSearch + '/' + this.$store.state.tokenDikti)
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
                      if ((this.isPpm && this.dosenApi.data[0].kode_pt === this.organisasi.kodePddikti) || !this.isPpm) {
                        apiServer
                          .get('/prodi/dikti/' + this.$store.state.tokenDikti + '/' + this.dosenApi.data[0].kode_pt + '/' + this.dosenApi.data[0].kode_prodi)
                          .then(response => {
                            var ptDetail = response.data
                            if (ptDetail.status === '200') this.dosen.programStudi = ptDetail.data[0].nama
                            this.toDosen(this.dosenApi)
                            this.dialog = false
                            this.showSnackbar('success', 'Pengambilan data berhasil, klik simpan untuk menyimpan perubahan')
                          })
                          .catch(error => {
                            console.log('fetch pt error:', error.response) // Logs out the error
                          })
                      } else {
                        this.showSnackbar('error', 'User tidak seinstitusi dengan anda, sinkronisasi dibatalkan')
                        this.dialog = false
                      }
                    }
                  })
                  .catch(error => {
                    console.log('There was an error:', error.response) // Logs out the error
                    this.showSnackbar('error', 'Terdapat gangguan koneksi ke server tujuan, coba lagi beberapa saat')
                    this.dialog = false
                  })
              } else {
                this.loginDikti()
              }
            } else {
              this.showSnackbar('danger', 'NIDN sudah digunakan, silahkan ketikkan NIDN lain')
              this.dialog = false
            }
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.dialog = false
          })
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
        this.getAllOrganisasi()
      },
      getAllOrganisasi () {
        this.listOrganisasi = []
        apiServer
          .get('/organisasi')
          .then(response => {
            response.data.forEach(this.populateListOrganisasi)
          })
          .catch(error => {
            console.log('There was an error list org:', error.response)
          })
      },
      getAllOrganisasiNonPoltekkes () {
        this.listOrganisasi = []
        // tambah data list organisasi apabila ada peneliti non nidn yg di add oleh user ppm yg mana is_poltekkes nya true
        if (this.isUbah && this.dosen.nidn === null && this.dosen.idOrganisasi !== null && this.dosen.isPoltekkes) {
          this.listOrganisasi.push({ value: this.dosen.idOrganisasi, text: this.dosen.namaInstitusi })
        }
        apiServer
          .get('/organisasi/nonPoltekkes')
          .then(response => {
            response.data.forEach(this.populateListOrganisasi)
          })
          .catch(error => {
            console.log('There was an error list org np:', error.response)
          })
      },
      populateListOrganisasi (value) {
        if (this.dosenApi && this.dosenApi.status === '200' && this.dosenApi.data[0].kode_pt === value.kodePddikti) this.dosen.idOrganisasi = value.id
        this.listOrganisasi.push({ value: value.id, text: value.nama })
      },
    },
  }
</script>

<style lang="scss" scoped>
  // .search{
  //   margin-left: 20%;
  // }
  td {
    border:none !important
  }

</style>
