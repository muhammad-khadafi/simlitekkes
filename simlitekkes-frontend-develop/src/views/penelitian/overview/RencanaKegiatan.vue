<template>
  <v-container fluid>
    <div>
      <v-col
        class="text-right mb-2"
      >
        <div class="flex-grow-1" />
        <v-dialog
          v-model="dialog"
          max-width="600px"
        >
          <template v-slot:activator="{ on }">
            <v-btn
              color="blue"
              class="white--text"
              v-on="on"
            >
              Tambah
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline"><span class="headline">{{ formTitle }}</span></span>
            </v-card-title>
            <v-divider />

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col
                    md="12"
                    lg="15"
                  >
                    <v-text-field
                      v-model="editedItem.namaKegiatan"
                      type="text"
                      label="Nama Kegiatan*"
                      :error-messages="$v.editedItem.namaKegiatan.$error ? ['Isian harus diisi'] : []"
                    />
                  </v-col>
                  <v-col
                    md="8"
                  >
                    <v-select
                      v-model="editedItem.tahun"
                      :items="dropdownTahun"
                      type="text"
                      label="Tahun*"
                      item-value="value"
                      :error-messages="$v.editedItem.tahun.$error ? ['Tahun harus dipilih'] : []"
                    />
                  </v-col>
                  <v-col md="6">
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
                          label="Tanggal Mulai*"
                          :error-messages="$v.editedItem.tanggalAwalJadwal.$error ? ['Tanggal harus diisi'] : []"
                          v-on="on"
                        />
                      </template>
                      <v-date-picker
                        ref="picker"
                        v-model="editedItem.tanggalAwalJadwal"
                        :min="startDate"
                        :max="endDate"
                        @change="save"
                        @input="menu = false"
                      />
                    </v-menu>
                  </v-col>
                  <v-col md="6">
                    <v-menu
                      ref="menu"
                      v-model="menu1"
                      :close-on-content-click="false"
                      transition="scale-transition"
                      offset-y
                      min-width="290px"
                    >
                      <template v-slot:activator="{ on }">
                        <v-text-field
                          v-model="computedTgl1"
                          prepend-icon="mdi-calendar-range"
                          readonly
                          label="Tanggal Akhir*"
                          :error-messages="$v.editedItem.tanggalAkhirJadwal.$error ? ['Tanggal harus diisi'] : []"
                          v-on="on"
                        />
                      </template>
                      <v-date-picker
                        ref="picker"
                        v-model="editedItem.tanggalAkhirJadwal"
                        :min="startDate"
                        :max="endDate"
                        @change="save"
                        @input="menu1 = false"
                      />
                    </v-menu>
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="text-right">
                    <v-card-actions>
                      <div class="flex-grow-1" />
                      <v-btn
                        color="blue darken-1 white--text"
                        @click="saveForm"
                      >
                        Simpan
                      </v-btn>
                      <v-btn
                        color="red darken-1 white--text"
                        @click="close"
                      >
                        Batal
                      </v-btn>
                    </v-card-actions>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions />
          </v-card>
        </v-dialog>
      </v-col>
      <v-expansion-panels
        :disabled="!listTahun.length"
      >
        <v-expansion-panel v-if="listTahun.length == 0 && isLoadFinished == true">
          <v-expansion-panel-header>
            <h1 class="body-1 text--primary">
              Tidak ada data jadwal kegiatan
            </h1>
          </v-expansion-panel-header>
        </v-expansion-panel>
        <v-expansion-panel
          v-for="(item,i) in listTahun"
          :key="i"
        >
          <v-expansion-panel-header>
            <h1 class="body-1 text--primary">
              Tahun {{ item }}
            </h1>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-data-table
              :headers="headers"
              :items="listRencanaKegiatanYearly[i]"
              sort-by="judul"
              class="elevation-1"
              :loading="isLoading"
              loading-text="Loading... Please wait"
              :search="search"
            >
              <template v-slot:top>
                <v-divider />
                <v-toolbar
                  flat
                  class="mt-1"
                >
                  <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Cari"
                    single-line
                    hide-details
                    class="search"
                  />
                </v-toolbar>
              </template>
              <template
                v-slot:item.action="{ item }"
              >
                <v-btn
                  small
                  color="blue darken-1"
                  text
                  @click="showUbah(item)"
                >
                  Ubah
                </v-btn>
                <v-btn
                  small
                  color="blue darken-1"
                  text
                  @click="deleteItem(item)"
                >
                  Hapus
                </v-btn>
              </template>
              <template v-slot:no-data />
            </v-data-table>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>

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
    </div>
  </v-container>
</template>

<script>
  import { apiServer } from '../../../setup-axios'
  import axios from 'axios'
  import moment from 'moment'
  import { required } from 'vuelidate/lib/validators'

  export default {
    data: () => ({
      search: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      menu: false,
      menu1: false,
      dialog: false,
      listRencanaKegiatanYearly: [],
      listTahun: [],
      editedItem: {
        id: null,
        idPenelitian: null,
        tanggalAwalJadwal: null,
        tanggalAkhirJadwal: null,
        tahun: null,
        namaKegiatan: '',
      },
      defaultItem: {
        id: null,
        idPenelitian: null,
        tanggalAwalJadwal: null,
        tanggalAkhirJadwal: null,
        tahun: null,
        namaKegiatan: '',
      },
      skemaPenelitian: {},
      dropdownTahun: [],
      headers: [
        {
          text: 'Nama Kegiatan',
          align: 'center',
          value: 'namaKegiatan',
        },
        {
          text: 'Tanggal Mulai',
          align: 'center',
          value: 'tanggalAwalJadwalF',
        },
        {
          text: 'Tanggal Selesai',
          align: 'center',
          value: 'tanggalAkhirJadwalF',
        },
        {
          text: 'Tahun Kegiatan',
          align: 'center',
          value: 'tahun',
        },
        { text: 'Aksi', value: 'action', align: 'center', sortable: false },
      ],
      editedIndex: -1,
      isLoadFinished: false,
    }),

    computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'Buat Jadwal Kegiatan' : 'Ubah Jadwal Kegiatan'
      },
      computedTgl () {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.editedItem.tanggalAwalJadwalFormatted = this.editedItem.tanggalAwalJadwal
        return this.editedItem.tanggalAwalJadwalFormatted
          ? moment(this.editedItem.tanggalAwalJadwalFormatted).format('DD-MM-YYYY')
          : ''
      },
      computedTgl1 () {
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.editedItem.tanggalAkhirJadwalFormatted = this.editedItem.tanggalAkhirJadwal
        return this.editedItem.tanggalAkhirJadwalFormatted
          ? moment(this.editedItem.tanggalAkhirJadwalFormatted).format('DD-MM-YYYY')
          : ''
      },
      startDate () {
        if (this.dropdownTahun.length > 0) {
          var dateStr = this.dropdownTahun[0].value + '01' + '01'
          var date = moment(dateStr).format('YYYY-MM-DD')
          return date
        } else {
          return ''
        }
      },
      endDate () {
        if (this.dropdownTahun.length > 0) {
          var dateStr = this.dropdownTahun[0].value + '12' + '31'
          var date = moment(dateStr).format('YYYY-MM-DD')
          return date
        } else {
          return ''
        }
      },
    },

    watch: {
      dialog (val) {
        val || this.close()
      },
      menu (val) {
        val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
      },
      menu1 (val) {
        val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
      },
    },

    created () {
      this.getData()
    },

    validations: {
      editedItem: {
        tanggalAwalJadwal: { required },
        tanggalAkhirJadwal: { required },
        tahun: { required },
        namaKegiatan: { required },
      },
    },

    methods: {
      save (date) {
        this.$refs.menu.save(date)
      },
      getData () {
        this.isLoading = true
        console.log(this.isLoading)
        this.listTahun = []
        this.listRencanaKegiatanYearly = []
        axios.all([
          apiServer.get('/jadwal-kegiatan/tahun/' + this.$route.params.id),
          apiServer.get('/penelitian/' + this.$route.params.id),
        ]).then(axios.spread((resp1, resp2) => {
          this.listTahun = resp1.data
          console.log('list tahun:', resp1.data) // For now, logs out the response
          for (var i = 0; i < this.listTahun.length; i++) {
            apiServer
              .get('/jadwal-kegiatan/' + this.$route.params.id + '/' + this.listTahun[i])
              .then(response => {
                this.listRencanaKegiatanYearly.push(response.data)
                response.data.forEach(this.formattingDate)
              })
          }
          this.isLoading = false
          this.fillDropdownTahun(resp2.data)
        })).catch(error => {
          console.log('Terdapat error:', error.response) // Logs out the error
        })
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
      fillDropdownTahun (item) {
        for (var i = item.tahunMulaiPelaksanaan; i <= item.tahunSelesaiPelaksanaan; i++) {
          this.dropdownTahun.push({ text: i, value: parseInt(i) })
        }
        this.isLoadFinished = true
      },
      tambahItem (item) {
        this.editedItem.idPenelitian = this.$route.params.id
        apiServer
          .post('/jadwal-kegiatan', item)
          .then(response => {
            this.editedItem = this.defaultItem
            this.getData()
            this.showSnackbar('success', 'Jadwal kegiatan berhasil ditambah')
            this.dialog = false
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Terdapat Kesalahan')
          })
      },
      ubahItem (item) {
        apiServer
          .put('/jadwal-kegiatan', item)
          .then(response => {
            this.editedItem = this.defaultItem
            this.getData()
            this.showSnackbar('success', 'Jadwal kegiatan berhasil diubah')
            this.dialog = false
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Terdapat Kesalahan')
          })
      },
      showUbah (item) {
        this.editedIndex = item.id
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },
      saveForm () {
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
              this.editedItem.username = this.$store.state.user.name
              console.log('submit form: ', this.editedItem)
              if (this.validateAll()) return
              this.editedItem.id != null ? this.ubahItem(this.editedItem) : this.tambahItem(this.editedItem)
            }
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      deleteItem (item) {
        confirm('Anda yakin menghapus ' + item.namaKegiatan + '?') &&
          this.hapusRencanaKegiatan(item)
      },
      hapusRencanaKegiatan (item) {
        this.editedItem.username = this.$store.state.user.name
        apiServer
          .delete('/jadwal-kegiatan/' + item.id + '/' + this.editedItem.username)
          .then(response => {
            this.getData()
            this.showSnackbar('success', 'Data berhasil dihapus')
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Error, please contact your administrator!')
          })
      },
      formattingDate (value) {
        value.tanggalAwalJadwalF = moment(value.tanggalAwalJadwal).format('DD-MM-YYYY')
        value.tanggalAwalJadwal = value.tanggalAwalJadwal
          ? moment(value.tanggalAwalJadwal).format('YYYY-MM-DD')
          : ''
        value.tanggalAkhirJadwalF = moment(value.tanggalAkhirJadwal).format('DD-MM-YYYY')
        value.tanggalAkhirJadwal = value.tanggalAkhirJadwal
          ? moment(value.tanggalAkhirJadwal).format('YYYY-MM-DD')
          : ''
      },
      close () {
        this.dialog = false
        this.$v.$reset()
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 300)
      },
      validateAll () {
        this.$v.$touch()
        this.errors = this.$v.editedItem.$anyError || this.$v.editedItem.$invalid
        var validate = !!this.errors
        return validate
      },
    },
  }
</script>

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }
</style>
