<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="listTopik"
        sort-by="nama"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar height="90px">
            <v-toolbar-title>
                <strong> Tema: {{ detailTema.nama }} </strong> <br> Daftar Topik
            </v-toolbar-title>
          </v-toolbar>
          <v-divider />
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
            <v-dialog
              v-model="dialog"
              max-width="600px"
            >
              <template v-slot:activator="{ on }">
                <v-btn
                  color="blue"
                  class="mb-2 white--text"
                  v-on="on"
                >
                  Tambah Topik
                </v-btn>
              </template>

              <v-card>
                <v-card-title>
                  <span class="headline">{{ formTitle }}</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-row no-gutters>
                      <v-col
                        md="12"
                        lg="15"
                      >
                        <v-text-field
                          v-model="$v.editedItem.nama.$model"
                          :error-messages="$v.editedItem.nama.$error ? ['Wajib Diisi'] : []"
                          label="Nama Topik*"
                        />
                      </v-col>
                      <v-col md="5">
                        <v-checkbox
                          v-model="editedItem.isActive"
                          label="Aktifkan?"
                        />
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <div class="flex-grow-1" />
                  <v-btn
                    color="blue darken-1"
                    text
                    @click="close"
                  >
                    Batal
                  </v-btn>
                  <v-btn
                    color="blue darken-1"
                    text
                    @click="save"
                  >
                    Simpan
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>
        <template v-slot:item.action="{ item }">
          <v-btn
            small
            color="blue darken-1"
            text
            @click="editItem(item)"
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
  import { apiServer } from '../setup-axios'
  import axios from 'axios'
  import { required } from 'vuelidate/lib/validators'

  export default {
    data: () => ({
      search: '',
      errors: false,
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      menu: false,
      dialog: false,
      listTopik: [],
      detailTema: {},
      headers: [
        {
          text: 'Topik',
          align: 'left',
          value: 'nama',
        },
        { text: 'Status', value: 'isActiveStr' },
        { text: 'Aksi', value: 'action', align: 'center', sortable: false },
      ],
      editedIndex: -1,
      editedItem: {
        idTema: null,
        nama: '',
        isActive: null,
        username: '',
      },
      defaultItem: {
        idTema: null,
        nama: '',
        isActive: null,
        username: '',
      },
      errorMsg: 'Terdapat kesalahan input',
    }),

    computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'Buat Topik' : 'Ubah Topik'
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
        nama: {
          required,
        },
      },
    },

    methods: {
      getData () {
        this.isLoading = true
        console.log(this.isLoading)
        axios.all([
          apiServer.get('/topik/' + this.$route.params.id),
          apiServer.get('/tema/' + this.$route.params.id),
        ]).then(axios.spread((topikList, detailTema) => {
          this.listTopik = topikList.data
          this.detailTema = detailTema.data
          this.isLoading = false
        }))
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
      editItem (item) {
        this.editedIndex = this.listTopik.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialog = true
      },
      close () {
        this.dialog = false
        this.$v.$reset()
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 300)
      },
      save () {
        this.$v.$touch()
        if (this.editedIndex > -1) {
          this.updateData()
        } else {
          this.postData()
        }
      },
      updateData () {
        this.editedItem.username = this.$store.state.user.name
        this.errors = this.$v.editedItem.$anyError
        if (this.errors === false) {
          apiServer
            .put('/topik', this.editedItem)
            .then(response => {
              this.getData()
              this.showSnackbar('success', 'Data berhasil diubah')
              this.close()
            })
            .catch(error => {
              if (error.response.status == 409) this.errorMsg = 'Topik ini sudah ada'
              console.log('There was an error:', error.response)
              this.showSnackbar('error', this.errorMsg)
            })
        }
      },
      postData () {
        this.errors = this.$v.editedItem.$error
        if (this.errors === false) {
          this.editedItem.idTema = this.$route.params.id
          this.editedItem.username = this.$store.state.user.name
          apiServer
            .post('/topik', this.editedItem)
            .then(response => {
              this.getData()
              this.showSnackbar('success', 'Data berhasil ditambah')
              this.close()
            })
            .catch(error => {
              console.log('There was an error:', error.response)
              if (error.response.status === 409) this.errorMsg = 'Topik ini sudah ada'
              this.showSnackbar('error', this.errorMsg)
            })
        }
      },
      deleteItem (item) {
        confirm('Anda yakin menghapus topik ini?') &&
          this.deleteData(item.id)
      },
      deleteData (id) {
        this.editedItem.username = this.$store.state.user.name
        apiServer
          .delete('/topik/' + id + '/' + this.editedItem.username)
          .then(response => {
            this.getData()
            this.showSnackbar('success', 'Data berhasil dihapus')
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Error, please contact your administrator!')
          })
      },
    },
  }
</script>

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }
</style>
