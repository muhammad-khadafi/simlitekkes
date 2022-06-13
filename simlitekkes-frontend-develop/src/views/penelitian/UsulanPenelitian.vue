<template>
  <v-container>
    <div v-if="hasAccess == false">
      <material-card>
        <page403 />
      </material-card>
    </div>
    <div v-if="hasAccess">
      <material-card
        color="blue"
        title="Persyaratan Umum"
      >
        <v-col>
          <v-row v-if="snackbar">
            <v-snackbar
              v-model="snackbar"
              :color="snackbarColor"
              :timeout="9000"
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
            <p class="body-1 text--primary">
              <v-icon
                v-if="isAllowed"
                color="green"
                x-large
              >
                mdi-check-circle
              </v-icon>
              <v-icon
                v-if="!isAllowed"
                color="red"
                x-large
              >
                mdi-minus-circle
              </v-icon>
              Memiliki NIDN Aktif/NITK/NIP
            </p>
          </v-row>
<!--          <v-row>
            <p class="body-1 text&#45;&#45;primary">
              <v-icon
                v-if="isBebasTanggungan"
                color="green"
                x-large
              >
                mdi-check-circle
              </v-icon>
              <v-icon
                v-if="!isBebasTanggungan"
                color="red"
                x-large
              >
                mdi-minus-circle
              </v-icon>
              Tanggungan Kegiatan (Tidak Ada)
            </p>
          </v-row>-->
        </v-col>
      </material-card>
      <material-card
        color="blue"
        title="Identitas Pengusul (Ketua)"
      >
        <v-row>
          <v-col
            cols="12"
            md="12"
          >
            <v-simple-table>
              <template v-slot:default>
                <tbody class="body-1 text--primary">
                <tr>
                  <td
                    style="width: 15%"
                  >
                    Nama
                  </td>
                  <td style="width: 5%">
                    :
                  </td>
                  <td>
                    {{ peneliti.gelarDepan }} {{ peneliti.nama }} {{ peneliti.gelarBelakang }}
                  </td>
                </tr>
                <tr>
                  <td>
                    NIDN
                  </td>
                  <td>
                    :
                  </td>
                  <td>
                    {{ peneliti.nidn }}
                  </td>
                </tr>
                <tr>
                  <td>
                    Institusi
                  </td>
                  <td>
                    :
                  </td>
                  <td>
                    {{ peneliti.namaInstitusi }}
                  </td>
                </tr>
                <tr>
                  <td>
                    Program Studi
                  </td>
                  <td>
                    :
                  </td>
                  <td>
                    {{ peneliti.programStudi }}
                  </td>
                </tr>
                <tr>
                  <td>
                    ID Sinta
                  </td>
                  <td>
                    :
                  </td>
                  <td>
                    -
                  </td>
                </tr>
                <tr>
                  <td>
                    Jenjang
                  </td>
                  <td>
                    :
                  </td>
                  <td>
                    {{ peneliti.jenjangPendidikanTerakhir }}
                  </td>
                </tr>
                <tr>
                  <td>
                    Email
                  </td>
                  <td>
                    :
                  </td>
                  <td>
                    {{ peneliti.email }}
                  </td>
                </tr>
                </tbody>
              </template>
            </v-simple-table>
          </v-col>
        </v-row>
      </material-card>
      <material-card
        color="blue"
        title="Rekam Jejak"
      >
        <v-row>
          <v-col
            cols="12"
            md="12"
            class="subtitle-1"
          >
            <ol
              type="1"
            >
              <li
                v-for="icon in rekamJejak"
                :key="icon.id"
                style="margin-bottom: 16px"
              >
                {{ icon.nama + " (" + icon.jumlahOutput + ")" }}
              </li>
            </ol>
          </v-col>
        </v-row>
      </material-card>
      <material-card
        color="blue"
        title="Data Usulan"
      >
        <v-row class="mx-lg-6">
          <v-col
            cols="12"
            md="6"
          >
            <v-select
              v-model="pengusulan.idKategori"
              :items="listKategori"
              label="Kategori Penelitian*"
              @change="onChangeListSkema()"
              :disabled="!isAllowed"
            />
          </v-col>
          <v-col
            cols="12"
            md="6"
          >
            <v-select
              v-model="pengusulan.idSkemaPenelitian"
              :items="listSkema"
              :disabled="!isDdKategoriPicked"
              label="Skema Penelitian*"
            />
          </v-col>
        </v-row>
        <v-row class="mx-lg-6">
          <v-col
            cols="12"
            md="12"
          >
            <v-text-field
              v-model="pengusulan.judul"
              label="Judul*"
              counter="250"
              :disabled="!isAllowed"
            />
          </v-col>
        </v-row>
        <v-row
          justify="end"
          class="ml-12 mt-6"
        >
          <v-btn
            color="blue"
            class="mb-2 white--text mr-2"
            :disabled="(pengusulan.idSkemaPenelitian == null || pengusulan.judul == '') ? true : false"
            @click="nextPage"
          >
            Lanjut
          </v-btn>
          <v-btn
            color="red"
            class="mb-2 white--text mr-2"
            @click="cancel"
          >
            Batal
          </v-btn>
        </v-row>
      </material-card><br>
    </div>
  </v-container>
</template>

<script>
  import { apiServer } from '../../setup-axios'
  import MaterialCard from '../../components/material/Card'
  import moment from 'moment'
  import axios from 'axios'

  export default {
    components: { MaterialCard, Page403: () => import('@/views/403.vue') },
    data: () => ({
      isUbah: false,
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isAllowed: null,
      isBebasTanggungan: true,
      isDdKategoriPicked: false,
      isDdSkemaPicked: false,
      listKategori: [],
      listSkema: [],
      pengusulan: {
        idKategori: null,
        idSkemaPenelitian: null,
        klasifikasi: null,
        judul: '',
        username: null,
      },
      workflow: {},
      timPeneliti: {},
      peneliti: {},
      rekamJejak: [],
      hasAccess: null,
      dosenApi: null,
      countFail: 0,
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

    created () {
      apiServer
        .get('/validasi/date/isAllowed')
        .then(response => {
          var pesanValidasi = response.data
          if (pesanValidasi !== 'Allowed') {
            this.$store.dispatch('setStatus', {
              snackbarColor: 'danger',
              snackbarText: pesanValidasi,
            })
            this.$router.replace('/usulan')
          } else {
            apiServer
              .get('/validasi/isPoltekkes/' + this.$store.state.user.organizationId)
              .then(response => {
                var isPoltekkes = response.data
                var isDosen = this.$store.state.user.role.roleCode === 'DOSEN'
                this.hasAccess = isPoltekkes && isDosen
                if (this.hasAccess) this.getData()
              })
              .catch(error => {
                console.log('There was an error:', error.response) // Logs out the error
              })
          }
        })
        .catch(error => {
          console.log('There was an error:', error.response) // Logs out the error
        })
    },
    methods: {
      getData () {
        apiServer
          .get('/organisasi/' + this.$store.state.user.organizationId)
          .then(response => {
            if (response.data != null && response.data.klasifikasi != null) {
              this.pengusulan.klasifikasi = response.data.klasifikasi
              axios.all([
                apiServer.get('/skemaPenelitian/kategori/' + this.pengusulan.klasifikasi),
                apiServer.get('/peneliti/user/' + this.$store.state.user.userId),
                apiServer.get('/jenis-output/info-jumlah/' + this.$store.state.user.userId),
                apiServer.get('/userweb-profil/' + this.$store.state.user.userId),
              ]).then(axios.spread((response1, response2, response3, response4) => {
                for (var x = 0; x < response1.data.length; x++) {
                  this.listKategori.push({ value: response1.data[x].id, text: response1.data[x].nama })
                }
                this.peneliti = response2.data
                var userweb = response4.data
                this.isAllowed = this.checkNip(userweb.nip)
                if (this.peneliti.nidn !== null && this.peneliti.nidn.substring(0, 2) === '77') {
                  this.isAllowed = this.isAllowed || this.checkNitk(this.peneliti.nidn)
                } else if (this.peneliti.nidn !== null) {
                  // this.getApiDosen()
                  this.isAllowed = this.isAllowed || this.checkNidn(this.peneliti.nidn)
                }
                this.rekamJejak = response3.data
              })).catch(error => {
                console.log('There was an error:', error.response)
              })
            }
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      updateHalaman () {
        this.isUbah = true
      },
      viewHalaman () {
        this.isUbah = false
      },
      back () {
        this.$router.replace('/skema-penelitian')
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
      updateData () {
        if (this.skema.jumlahDanaMin != null) {
          this.skema.jumlahDanaMin = this.skema.jumlahDanaMin * 1000000
        }
        if (this.skema.jumlahDanaMax != null) {
          this.skema.jumlahDanaMax = this.skema.jumlahDanaMax * 1000000
        }
        apiServer
          .put('/skemaPenelitian', this.skema)
          .then(response => {
            this.getData()
            this.showSnackbar('success', 'Data berhasil diubah')
            this.isUbah = false
            this.getData()
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Terdapat kesalahan input!')
          })
      },
      onChangeListSkema () {
        this.isDdKategoriPicked = true
        this.pengusulan.idSkemaPenelitian = null
        this.listSkema = []
        apiServer
          .get('/skemaPenelitian/' + this.pengusulan.idKategori + '/' + this.pengusulan.klasifikasi)
          .then(response => {
            for (var x = 0; x < response.data.length; x++) {
              this.listSkema.push({ value: response.data[x].id, text: response.data[x].nama })
            }
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      nextPage () {
        this.pengusulan.username = this.$store.state.user.username
        apiServer
          .post('/penelitian', this.pengusulan)
          .then(response => {
            var idPenelitian = response.data
            this.timPeneliti.idPenelitian = idPenelitian
            this.timPeneliti.idPeneliti = this.peneliti.id
            this.timPeneliti.idPeranPeneliti = 1
            this.timPeneliti.idOrganisasi = this.$store.state.user.organizationId
            this.timPeneliti.username = this.$store.state.user.username
            this.workflow.idPenelitian = idPenelitian
            this.workflow.idStatusTahapPenelitian = 1
            this.workflow.timestampUpdate = new Date()
            this.workflow.isLast = true
            this.workflow.username = this.$store.state.user.username
            axios.all([
              apiServer.post('/tim-peneliti', this.timPeneliti),
              apiServer.post('/workflow-penelitian', this.workflow),
            ]).then(axios.spread((response, response1) => {
              this.$router.push('/penelitian/' + idPenelitian + '/identitas-usulan')
              this.showSnackbar('success', 'Data penelitian berhasil ditambah')
            }))
          })
          .catch(error => {
            console.log('There was an error:', error.response)
          })
      },
      cancel () {
        this.$router.replace('/')
      },
      loginDikti () {
        apiServer
          .get('/peneliti/dikti/login')
          .then(response => {
            this.$store.dispatch('setTokenPddikti', response.data)
            this.$store.dispatch('setTokenPddikti', response.data)
            this.getApiDosen()
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.showSnackbar('error', 'Terdapat gangguan koneksi ke server, coba lagi dengan me-refresh page')
          })
      },
      getApiDosen () {
        if (this.$store.state.tokenDikti) {
          apiServer
            .get('/peneliti/dikti/' + this.peneliti.nidn + '/' + this.$store.state.tokenDikti)
            .then(response => {
              console.log('status get api:', response.data.status)
              if (response.data.status === '401' || response.data.status === '500') {
                this.countFail++
                if (this.countFail === 5) {
                  this.countFail = 0
                  this.showSnackbar('error', 'Terdapat gangguan koneksi ke server, coba lagi dengan me-refresh page')
                } else if (!this.$store.state.tokenDikti || (this.$store.state.tokenDikti && response.data.status !== '200')) {
                  this.loginDikti()
                }
              } else if (response.data.status === '404') {
                this.countFail = 0
                this.showSnackbar('error', 'NIDN tidak ditemukan')
              } else {
                this.countFail = 0
                this.dosenApi = response.data
                this.isAllowed = this.checkNidn(this.peneliti.nidn) || this.isAllowed
              }
            })
            .catch(error => {
              console.log('There was an error:', error.response) // Logs out the error
              this.showSnackbar('error', 'Terdapat gangguan di server tujuan, coba lagi beberapa saat')
              this.dialog = false
            })
        } else {
          this.loginDikti()
        }
      },
      checkNip (nip) {
        var allowed = (nip !== null && nip.length === 18 && !isNaN(nip))
        console.log('ceknip: ' + allowed)
        return allowed
      },
      checkNitk (nitk) {
        var allowed = (nitk !== null && nitk.length === 10 && !isNaN(nitk))
        console.log('ceknitk: ' + allowed)
        return allowed
      },
      checkNidn (nidn) {
        var allowed = nidn !== null && nidn.length === 10 && !isNaN(nidn)
        // var aktif = this.dosenApi !== null && this.dosenApi.data[0].status_keaktifan.nama === 'Aktif'
        var aktif = this.peneliti.isActive
        console.log('ceknidnformat: ' + allowed + 'ceknidnaktif: ' + aktif)
        return allowed && aktif
      },
    },
  }
</script>

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }

</style>
