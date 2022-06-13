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
                    <v-select
                      v-model="editedItem.idJenisMitra"
                      :items="listJenisMitra"
                      type="text"
                      label="Jenis Mitra*"
                      item-value="value"
                      :error-messages="$v.editedItem.idJenisMitra.$error ? ['Jenis mitra belum dipilih'] : []"
                    />
                  </v-col>
                  <v-col
                    md="12"
                  >
                    <v-text-field
                      v-model="editedItem.namaMitra"
                      type="text"
                      label="Nama Mitra"
                    />
                  </v-col>
                  <v-col
                    md="12"
                  >
                    <v-text-field
                      v-model="editedItem.email"
                      type="text"
                      label="Email*"
                      :error-messages="$v.editedItem.email.$error ? ['Email tidak boleh kosong & harus sesuai format'] : []"
                    />
                  </v-col>
                  <v-col
                    md="12"
                  >
                    <v-text-field
                      v-model="editedItem.penanggungJawab"
                      type="text"
                      label="Penanggung Jawab*"
                      :error-messages="$v.editedItem.penanggungJawab.$error ? ['Penanggung jawab tidak boleh kosong'] : []"
                    />
                  </v-col>
                  <v-col md="12">
                    <h2>Kontribusi Dana (incash)</h2>
                    <v-divider />
                  </v-col>
                  <v-col
                    v-for="(item,i) in dropdownTahun"
                    :key="'A' + i"
                    md="4"
                  >
                    <v-text-field
                      v-model="listJumlahKontribusi[i]"
                      type="number"
                      :label="'Kontribusi Tahun Ke ' + (i + 1)"
                    />
                  </v-col>
                  <v-col md="12">
                    <h2>Kontribusi Barang (inkind)</h2>
                    <v-divider />
                  </v-col>
                  <v-col
                    v-for="(item,i) in dropdownTahun"
                    :key="'B' + i"
                    md="12"
                  >
                    <v-textarea
                      v-model="listBarangKontribusi[i]"
                      type="text"
                      :label="'Barang Kontribusi Tahun Ke ' + (i + 1)"
                    />
                  </v-col>
                  <v-col
                    md="7"
                  >
                    <v-file-input
                      ref="fileInput"
                      v-model="fileUpload"
                      label="Upload File MOU*"
                      accept="application/pdf"
                      :rules="rules"
                      required
                      :error-messages="fileUploadErrors"
                      @change="onFilePicked"
                      @input="$v.fileUpload.$touch()"
                      @blur="$v.fileUpload.$touch()"
                    />
                  </v-col>
                </v-row>
                <v-row>
                  <v-col class="text-right">
                    <v-card-actions>
                      <div class="flex-grow-1" />
                      <v-btn
                        color="blue darken-1 white--text"
                        @click="saveForm"
                        :loading="loading"
                        :disabled="loading"
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

      <v-data-table
        :headers="headers"
        :items="listMitraPenelitian"
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
          v-slot:item.actionDownload="{ item }"
        >
          <v-btn
            small
            color="blue darken-1"
            text
            :disabled="item.filePath == null || item.filePath == ''"
            @click="downloadItem(item)"
          >
            Download
          </v-btn>
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
        <template v-slot:item.listKontri="{ item }">
          <div
            v-for="kontri in item.listKontri"
            :key="kontri.tahun"
          >
            <div v-if="kontri.barangKontribusi">
              {{ kontri.tahun }} : {{ kontri.barangKontribusi }}
            </div>
            <div v-if="!kontri.barangKontribusi">
              {{ kontri.tahun }} : -
            </div>
          </div>
          <div v-if="item.listKontri.length < 1">
            -
          </div>
        </template>
        <template v-slot:no-data />
        <template v-slot:item.listKontri2="{ item }">
          <div
            v-for="kontri in item.listKontri"
            :key="kontri.tahun"
          >
            <div v-if="kontri.jumlahKontribusi">
              {{ kontri.tahun }} : {{ thousandSeparator(kontri.jumlahKontribusi) }}
            </div>
            <div v-if="!kontri.jumlahKontribusi">
              {{ kontri.tahun }} : -
            </div>
          </div>
          <div v-if="item.listKontri.length < 1">
            -
          </div>
        </template>
        <template v-slot:no-data />
      </v-data-table>

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
  import { apiServer, fileUrl } from '../../../setup-axios'
  import axios from 'axios'
  import { required, email } from 'vuelidate/lib/validators'
  import CommonService from '../../../services/CommonService'

  export default {
    data: () => ({
      search: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      menu: false,
      dialog: false,
      listMitraPenelitian: [],
      listJenisMitra: [],
      editedItem: {
        id: null,
        idPenelitian: null,
        idJenisMitra: '',
        namaMitra: '',
        email: '',
        penanggungJawab: '',
        filePath: '',
      },
      defaultItem: {
        id: null,
        idPenelitian: null,
        idJenisMitra: '',
        namaMitra: '',
        email: '',
        penanggungJawab: '',
        filePath: '',
        fileName: '',
      },
      skemaPenelitian: {},
      dropdownTahun: [],
      headers: [
        {
          text: 'Nama Mitra',
          align: 'center',
          value: 'namaMitra',
        },
        {
          text: 'Jenis Mitra',
          align: 'center',
          value: 'jenisMitraStr',
        },
        {
          text: 'Email',
          align: 'center',
          value: 'email',
        },
        {
          text: 'Penanggung Jawab',
          align: 'center',
          value: 'penanggungJawab',
        },
        {
          text: 'Jumlah Kontribusi',
          align: 'center',
          value: 'jumlahKontribusiStr',
        },
        {
          text: 'Detail Per Tahun',
          align: 'center',
          value: 'listKontri2',
        },
        {
          text: 'Barang Kontribusi',
          align: 'center',
          value: 'listKontri',
        },
        { text: 'MOU', value: 'actionDownload', align: 'center', sortable: false },
        { text: 'Aksi', value: 'action', align: 'center', sortable: false },
      ],
      editedIndex: -1,
      listJumlahKontribusi: [],
      listBarangKontribusi: [],
      // listJumlahKontribusiSeparator: [],
      fileUpload: null,
      loading: false,
      rules: [
        value =>
          !value ||
          value.size <= 1000000 ||
          'File harus bertipe pdf dengan ukuran kurang dari 1MB',
      ],
    }),

    computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'Buat Mitra Penelitian' : 'Ubah Mitra Penelitian'
      },
      fileUploadErrors () {
        const errors = []
        if (!this.$v.fileUpload.$dirty) return errors
        !this.$v.fileUpload.required && errors.push('File harus di isi')
        return errors
      },
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
    },

    created () {
      this.getData()
    },

    validations: {
      editedItem: {
        idJenisMitra: { required },
        email: { required, email },
        penanggungJawab: { required },
      },
      fileUpload: { required },
    },

    methods: {
      save (date) {
        this.$refs.menu.save(date)
      },
      resetData () {
        this.listJenisMitra = []
        this.listMitraPenelitian = []
        this.dropdownTahun = []
        this.listJumlahKontribusi = []
        this.listBarangKontribusi = []
        this.fileUpload = null
      },
      getData () {
        this.isLoading = true
        console.log(this.isLoading)
        this.resetData()
        axios.all([
          apiServer.get('/jenis-mitra'),
          apiServer.get('/penelitian/' + this.$route.params.id),
          apiServer.get('/mitra-penelitian/' + this.$route.params.id + '/' + this.$store.state.user.organizationId + '/' + this.$store.state.user.role.roleCode + '/' + this.$store.state.user.userId),
        ]).then(axios.spread((resp1, resp2, resp3) => {
          resp1.data.forEach(this.populateJenisMitra)
          this.listMitraPenelitian = resp3.data
          this.fillDropdownTahun(resp2.data)
          this.isLoading = false
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
        for (var i = 1; i <= ((item.tahunSelesaiPelaksanaan - item.tahunMulaiPelaksanaan) + 1); i++) {
          this.dropdownTahun.push({ value: i, text: i })
        }
      },
      tambahItem (item) {
        this.editedItem.idPenelitian = this.$route.params.id
        this.editedItem.timestampUpload = new Date()
        const formData = new FormData()
        formData.append('file', this.fileUpload)
        apiServer
          .post('/mitra-penelitian/file', formData)
          .then(response => {
            this.editedItem.filePath = response.data.filePath
            apiServer
              .post('/mitra-penelitian', item)
              .then(response => {
                this.editedItem = this.defaultItem
                var listKontribusi = []
                var comparableLIst
                if (this.listJumlahKontribusi.length >= this.listBarangKontribusi.length) {
                  comparableLIst = this.listJumlahKontribusi
                } else {
                  comparableLIst = this.listBarangKontribusi
                }
                for (var index in comparableLIst) {
                  var kontribusi = {}
                  kontribusi.username = this.$store.state.user.name
                  kontribusi.idMitraPenelitian = response.data
                  kontribusi.idPenelitian = this.$route.params.id
                  kontribusi.tahun = parseInt(index) + 1
                  kontribusi.jumlahKontribusi = this.listJumlahKontribusi[index]
                  kontribusi.barangKontribusi = (this.listBarangKontribusi[index] ? this.listBarangKontribusi[index] : null)
                  listKontribusi.push(kontribusi)
                }
                apiServer
                  .post('/kontribusi-mitra/list', listKontribusi)
                  .then(response => {
                    this.getData()
                    this.showSnackbar('success', 'Mitra penelitian berhasil ditambah')
                    this.dialog = false
                  })
                  .catch(error => {
                    console.log('There was an error:', error.response)
                    this.showSnackbar('error', 'Terdapat Kesalahan')
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
      ubahItem () {
        if (this.fileUpload.name !== this.editedItem.fileName) {
          const formData = new FormData()
          formData.append('file', this.fileUpload)
          axios.all([
            apiServer.post('/mitra-penelitian/file', formData),
            apiServer.delete('/mitra-penelitian/file/' + this.editedItem.id),
          ]).then(axios.spread((response1, response2) => {
            this.editedItem.filePath = response1.data.filePath
            this.editedItem.timestampUpload = new Date()
            this.updateRowData(this.editedItem)
          })).catch(error => {
            console.log('There was an error:', error)
          })
        } else {
          this.updateRowData(this.editedItem)
        }
      },
      updateRowData (item) {
        apiServer
          .put('/mitra-penelitian', item)
          .then(response => {
            this.editedItem = this.defaultItem
            var listKontribusi = []
            var comparableLIst
            if (this.listJumlahKontribusi.length >= this.listBarangKontribusi.length) {
              comparableLIst = this.listJumlahKontribusi
            } else {
              comparableLIst = this.listBarangKontribusi
            }

            for (var index in comparableLIst) {
              var kontribusi = {}
              kontribusi.username = item.username
              kontribusi.idMitraPenelitian = item.id
              kontribusi.idPenelitian = this.$route.params.id
              kontribusi.tahun = parseInt(index) + 1
              kontribusi.jumlahKontribusi = this.listJumlahKontribusi[index]
              kontribusi.barangKontribusi = this.listBarangKontribusi[index] ? this.listBarangKontribusi[index] : null
              listKontribusi.push(kontribusi)
            }

            apiServer
              .put('/kontribusi-mitra/list/' + item.id, listKontribusi)
              .then(response => {
                this.getData()
                this.showSnackbar('success', 'Mitra penelitian berhasil diubah')
                this.dialog = false
              })
              .catch(error => {
                console.log('There was an error:', error.response)
                this.showSnackbar('error', 'Terdapat Kesalahan')
              })
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Terdapat Kesalahan')
          })
      },
      showUbah (item) {
        this.listJumlahKontribusi = []
        this.editedIndex = item.id
        this.editedItem = Object.assign({}, item)
        this.dialog = true
        apiServer
          .get('/mitra-penelitian/file/' + item.id)
          .then(response => {
            this.fileUpload = CommonService.dataURLtoFile(
              response.data,
              this.editedItem.fileName
            )
            const fileReader = new FileReader()
            fileReader.readAsDataURL(this.fileUpload)
            if (!this.fileUpload.name) this.fileUpload = null
          })
          .catch(error => {
            console.log('there was an error: ' + error.response)
            this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
          })
        apiServer
          .get('/kontribusi-mitra/list/' + item.id)
          .then(response => {
            for (var i = 0; i < this.dropdownTahun.length; i++) {
              var it = 0
              for (var val of response.data) {
                it++
                if ((i + 1) === val.tahun) {
                  this.listJumlahKontribusi.push(val.jumlahKontribusi)
                  this.listBarangKontribusi.push(val.barangKontribusi)
                  break
                } else if (it === response.data.length) {
                  this.listJumlahKontribusi.push(null)
                  this.listBarangKontribusi.push(null)
                }
              }
            }
          })
          .catch(error => {
            console.log('there was an error: ' + error.response)
            this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
          })
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
              console.log('submit form: ', this.$v)
              if (this.validateAll()) return
              this.loading = true
              setTimeout(() => (this.loading = false), 10000)
              this.editedItem.id != null ? this.ubahItem() : this.tambahItem(this.editedItem)
            }
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      deleteItem (item) {
        confirm('Anda yakin menghapus ' + item.namaMitra + '?') &&
          this.hapusMitraPenelitian(item)
      },
      hapusMitraPenelitian (item) {
        this.editedItem.username = this.$store.state.user.name
        apiServer
          .delete('/kontribusi-mitra/' + item.id + '/' + this.editedItem.username)
          .then(response => {
            apiServer
              .delete('/mitra-penelitian/' + item.id + '/' + this.editedItem.username)
              .then(response => {
                this.getData()
                this.showSnackbar('success', 'Data berhasil dihapus')
              })
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Error, There was an error!')
          })
      },
      close () {
        this.dialog = false
        this.$v.$reset()
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.listBarangKontribusi = []
          this.listJumlahKontribusi = []
          this.fileUpload = null
          this.editedIndex = -1
        }, 300)
      },
      validateAll () {
        this.$v.$touch()
        this.errors = this.$v.editedItem.$anyError || this.$v.editedItem.$invalid || this.$v.fileUpload.$error
        var validate = !!this.errors
        return validate
      },
      downloadItem (item) {
        apiServer.post('/mitra-penelitian/download/' + item.id)
          .then((resp) => {
            const ticket = resp.data.ticket
            window.location = fileUrl + '/download/mitra-penelitian/' + item.id + '?ticket=' + ticket
          })
      },
      populateJenisMitra (value) {
        this.listJenisMitra.push({ value: value.id, text: value.nama })
      },
      onFilePicked (event) {
        console.log('File at picked ' + this.fileUpload)
      },
      /* onBlurNumber (index) {
        this.listJumlahKontribusiSeparator[index] = this.listJumlahKontribusiSeparator[index].replace(/\D/g, '')
        this.listJumlahKontribusiSeparator[index] = this.thousandSeparator(this.listJumlahKontribusiSeparator[index], index)
      },
      thousandSeparator (amount, index) {
        this.listJumlahKontribusi[index] = this.listJumlahKontribusiSeparator[index].replace(/\D/g, '')
        if (amount !== '' || amount !== undefined || amount !== 0 || amount !== '0' || amount !== null) {
          return amount.toString().replace(/\./g, '.').replace(/\B(?=(\d{3})+(?!\d))/g, '.')
        } else {
          return amount
        }
      }, */
      thousandSeparator (amount) {
        if (amount !== '' || amount !== undefined || amount !== 0 || amount !== '0' || amount !== null) {
          return amount.toString().replace(/\./g, '.').replace(/\B(?=(\d{3})+(?!\d))/g, '.')
        } else {
          return amount
        }
      },
    },
  }
</script>

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }
</style>
