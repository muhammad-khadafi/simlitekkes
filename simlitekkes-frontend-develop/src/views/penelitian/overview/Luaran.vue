<template>
  <v-container fluid>
    <material-card
      color="blue"
      title="Luaran dan Target Usulan"
    >
      <v-row>
        <v-col class="text-right">
          <div class="flex-grow-1" />
          <v-btn
            v-if="isEdit"
            color="blue"
            class="white--text"
            @click.stop="add"
          >
            Tambah
          </v-btn>
        </v-col>
      </v-row>
      <v-dialog
        v-if="isEdit"
        v-model="dialog"
        max-width="600px"
      >
        <v-form
          ref="form"
          v-model="form"
        >
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>
            <v-divider />
            <v-card-text>
              <v-container>
                <v-row v-if="pickFormMode">
                  <v-col>
                    <span>Pilih Kategori Luaran:</span>
                  </v-col>
                </v-row>
                <v-row v-if="pickFormMode">
                  <v-col>
                    <v-btn
                      color="blue"
                      class="white--text"
                      :disabled="!couldAddWajib"
                      @click="formMode(true)"
                    >
                      Luaran Wajib
                    </v-btn>
                  </v-col>
                  <v-col>
                    <v-btn
                      color="blue"
                      class="white--text"
                      @click="formMode(false)"
                      :disabled="!isLuaranWajibDone"
                    >
                      Luaran Tambahan
                    </v-btn>
                  </v-col>
                  <v-col>
                    <div class="flex-grow-1" />
                  </v-col>
                </v-row>
                <v-row v-if="!pickFormMode">
                  <v-col>
                    <v-select
                      v-if="editWajib"
                      v-model="editedItem.idJenisOutput"
                      :items="dropdownJenisWajib"
                      type="text"
                      label="Jenis Luaran"
                      item-value="id"
                      item-text="nama"
                      :rules="rules"
                    />
                    <v-select
                      v-if="!editWajib"
                      v-model="editedItem.idJenisOutput"
                      :items="dropdownJenisTambahan"
                      type="text"
                      label="Jenis Luaran"
                      item-value="id"
                      item-text="nama"
                      :rules="rules"
                    />
                  </v-col>
                </v-row>
                <v-row v-if="!pickFormMode">
                  <v-col>
                    <v-select
                      v-if="editWajib"
                      v-model="editedItem.tahunTarget"
                      :items="dropdownTahunWajib"
                      type="text"
                      label="Tahun Target"
                      item-value="value"
                      item-text="value"
                      :rules="rules"
                    />
                    <v-select
                      v-if="!editWajib"
                      v-model="editedItem.tahunTarget"
                      :items="dropdownTahunTarget"
                      type="text"
                      label="Tahun Target"
                      item-value="value"
                      item-text="value"
                      :rules="rules"
                    />
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
            <v-card-actions>
              <v-container>
                <v-row>
                  <v-col class="text-right">
                    <div class="flex-grow-1" />
                    <v-btn
                      color="blue darken-1"
                      class="mb-2 white--text mr-2"
                      @click="saveForm"
                    >
                      Simpan
                    </v-btn>
                    <v-btn
                      color="red darken-1"
                      class="mb-2 white--text mr-2"
                      @click="close"
                    >
                      Batal
                    </v-btn>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-actions>
          </v-card>
        </v-form>
      </v-dialog>
      <v-row>
        <v-col>
          <v-data-table
            :headers="headers"
            :items="listLuaran"
            class="elevation-1"
            :loading="isLoading"
            loading-text="Loading... Please wait"
            :search="search"
          >
            <template v-slot:top>
              <v-toolbar
                flat
                class="mt-1"
              >
                <v-text-field
                  v-model="search"
                  append-icon="mdi-magnify"
                  label="Search"
                  single-line
                  hide-details
                  class="search"
                />
                <div class="flex-grow-1" />
              </v-toolbar>
            </template>
            <template
              v-slot:item.action="{ item }"
              style="width: 10%"
            >
              <v-btn
                class="pl-0"
                small
                color="blue darken-1"
                text
                :disabled="!isEdit"
                @click="editItem(item)"
              >
                Ubah
              </v-btn>
              <v-btn
                class="pl-0"
                small
                color="blue darken-1"
                text
                :disabled="!isEdit"
                @click="deleteItem(item)"
              >
                Hapus
              </v-btn>
            </template>
          </v-data-table>
        </v-col>
      </v-row>
    </material-card>
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
  import axios from 'axios'
  import { apiServer } from '../../../setup-axios'

  export default {
    data: () => ({
      search: '',
      dialog: false,
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      idPenelitian: null,
      isEdit: false,
      listLuaran: [],
      isLoading: true,
      headers: [
        { text: 'Tahun Target', value: 'tahunTarget', align: 'center' },
        { text: 'Jenis Output', value: 'nama' },
        { text: 'Wajib', value: 'wajib', align: 'center' },
        { text: 'Tambahan', value: 'tambahan', align: 'center' },
        { text: 'Aksi', value: 'action', sortable: false, align: 'center' },
      ],
      editedIndex: -1,
      editWajib: false,
      pickFormMode: false,
      editedItem: {
        id: null,
        idJenisOutput: null,
        idPenelitian: null,
        isTambahan: false,
        isWajib: true,
        nama: '',
        tahunRealisasi: '',
        tahunTarget: '',
      },
      defaultItem: {
        id: null,
        idJenisOutput: null,
        idPenelitian: null,
        isTambahan: false,
        isWajib: true,
        nama: '',
        tahunRealisasi: '',
        tahunTarget: '',
      },
      dropdownJenisWajib: [],
      dropdownJenisTambahan: [],
      dropdownTahunTarget: [],
      listTahunWajib: [],
      dropdownTahunWajib: [],
      form: false,
      rules: [
        value => !!value || 'Kolom Ini Harus Terisi!',
      ],
      isLuaranWajibDone: true
    }),
    computed: {
      formTitle () {
        var tempTitle = this.editedIndex === -1 ? 'Tambah Target Luaran' : 'Ubah Target Luaran'
        if (!this.pickFormMode) {
          tempTitle = this.editWajib ? tempTitle + ' Wajib' : tempTitle + ' Tambahan'
        }
        return tempTitle
      },
      couldAddWajib () {
        return this.dropdownTahunWajib.length > 0
      },
    },
    watch: {
      dialog (val) {
        val || this.close()
      },
    },
    created () {
      apiServer
        .get('/validasi/h14w/' + this.$route.params.id + '/' + this.$store.state.user.userId)
        .then(response => {
          var draft = response.data
          if (this.$store.state.user.role.roleCode === 'DOSEN' && draft) {
            this.isEdit = true
          } else {
            this.isEdit = false
          }
          this.getDataLuaran()
        })
        .catch(error => {
          console.log('There was an error:', error.response) // Logs out the error
        })
    },
    methods: {
      getDataLuaran () {
        this.isLoading = true
        this.idPenelitian = parseInt(this.$route.params.id)
        apiServer
          .get('/penelitian/' + this.$route.params.id)
          .then(response => {
            this.$store.dispatch('setPenelitian', response.data)
            var tahunMulai = parseInt(response.data.tahunMulaiPelaksanaan)
            var tahunSelesai = parseInt(response.data.tahunSelesaiPelaksanaan)
            this.dropdownTahunTarget = []
            for (var i = tahunMulai; i <= tahunSelesai; i++) {
              this.dropdownTahunTarget.push(String(i))
            }
            var idOrganisasi = this.$store.state.user.organizationId != null ? this.$store.state.user.organizationId : 0
            var role = this.$store.state.user.role.roleCode
            var idUser = this.$store.state.user.userId
            axios.all([
              apiServer.get('/target-output-penelitian/' +
                this.idPenelitian + '/' +
                idOrganisasi + '/' +
                role + '/' +
                idUser),
              apiServer.get('/jenis-output/list-wajib/' + response.data.idSkemaPenelitian + '/' + this.$route.params.id),
              apiServer.get('/jenis-output/list-tambahan/' + response.data.idSkemaPenelitian + '/' + this.$route.params.id),
            ])
              .then(axios.spread((response1, response2, response3) => {
                response1.data.map(value => {
                  value.wajib = value.isWajib ? 'V' : '-'
                  value.tambahan = value.isTambahan ? 'V' : '-'
                })
                this.listLuaran = response1.data
                this.listTahunWajib = []
                for (var i = 0; i < this.listLuaran.length; i++) {
                  if (this.listLuaran[i].isWajib) {
                    this.listTahunWajib.push(this.listLuaran[i].tahunTarget)
                  }
                }
                this.dropdownTahunWajib = this.dropdownTahunTarget
                this.dropdownTahunWajib = this.dropdownTahunWajib.filter(this.filterTahun)
                this.dropdownJenisWajib = response2.data
                this.dropdownJenisTambahan = response3.data
                this.isLoading = false
              }))
              .catch(error1 => {
                console.log('There was an error:', error1.response) // Logs out the error
                this.isLoading = false
              })
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      filterTahun (tahun) {
        var check = true
        for (var i = 0; i < this.listTahunWajib.length; i++) {
          check = check && this.listTahunWajib[i] !== tahun
        }
        return check
      },
      saveForm () {
        this.$refs.form.validate()
        if (this.form) {
          this.editedItem.nama = ''
          this.editedItem.username = this.$store.state.user.username
          this.editedItem.idPenelitian = this.idPenelitian
          console.log('editedItem,', this.editedItem)
          if (this.editedIndex === -1) {
            this.postData()
          } else {
            this.updateData()
          }
        } else {
          this.showSnackbar('error', 'Isian Form Tidak Memenuhi Syarat')
        }
      },
      add () {
        this.validasiLuaranWajib()
      },
      validasiLuaranWajib(){
        apiServer
        .get('/validasi-luaran-wajib/' + this.$route.params.id)
        .then(response => {
          this.isLuaranWajibDone = response.data
          this.editWajib = true
          this.pickFormMode = true
          this.dialog = true
        })
        .catch(error => {
          console.log('There was an error:', error.response) // Logs out the error
        })
      },
      formMode (wajib) {
        this.editWajib = wajib
        this.editedItem.isWajib = wajib
        this.editedItem.isTambahan = !wajib
        this.pickFormMode = false
      },
      editItem (item) {
        var tempItem = {
          id: item.idJenisOutput,
          nama: item.nama,
        }
        this.dropdownJenisWajib.push(tempItem)
        this.dropdownJenisTambahan.push(tempItem)
        this.dropdownTahunWajib.push(item.tahunTarget)
        this.editedIndex = item.id
        this.editWajib = item.isWajib
        this.pickFormMode = false
        this.editedItem = item
        this.dialog = true
      },
      postData () {
        apiServer
          .post('/target-output-penelitian', this.editedItem)
          .then(response => {
            this.close()
            this.showSnackbar('success', 'Berhasil Menambah Item!')
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Terdapat Kesalahan')
          })
      },
      updateData () {
        apiServer
          .put('/target-output-penelitian', this.editedItem)
          .then(response => {
            this.close()
            this.showSnackbar('success', 'Berhasil Mengubah Item!')
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Terdapat Kesalahan')
          })
      },
      deleteItem (item) {
        apiServer
          .delete('/target-output-penelitian/' + item.id + '/' + this.$store.state.user.username)
          .then(response => {
            this.getDataLuaran()
            this.showSnackbar('success', 'Berhasil Menghapus Item!')
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Terdapat Kesalahan')
          })
      },
      close () {
        this.editedIndex = -1
        this.editedItem = this.defaultItem
        this.dialog = false
        this.getDataLuaran()
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
    },
  }
</script>
