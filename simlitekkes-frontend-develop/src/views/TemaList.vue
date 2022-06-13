<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="listTema"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Tema</v-toolbar-title>
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
                    Tambah Tema
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
                            v-model="editedItem.nama"
                            type="text"
                            label="Nama Tema*"
                            :error-messages="$v.editedItem.nama.$error ? ['Isian harus diisi'] : []"
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
          </v-toolbar>
        </template>
        <template v-slot:item.action="{ item }">
          <v-btn
            small
            color="blue darken-1"
            text
            @click="listTopik(item)"
          >
            Daftar Topik
          </v-btn>
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
  import { required } from 'vuelidate/lib/validators'
  import axios from 'axios'

  export default {
    data: () => ({
      search: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      menu: false,
      dialog: false,
      listTema: [],
      headers: [
        {
          text: 'Nama Tema',
          align: 'left',
          value: 'nama',
        },
        { text: 'Aksi', value: 'action', align: 'center', sortable: false },
      ],
      editedIndex: -1,
      editedItem: {
        nama: '',
      },
      defaultItem: {
        nama: '',
      },
    }),
    computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'Buat Tema' : 'Ubah Tema'
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
        nama: { required },
      },
    },

    methods: {
      getData () {
        this.isLoading = true
        console.log(this.isLoading)
        axios.all([
          apiServer.get('/tema'),
        ]).then(axios.spread((response) => {
          this.listTema = response.data
          console.log('tema:', response.data) // For now, logs out the response
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
      listTopik (item) {
        this.$router.push('/topik/' + item.id)
      },
      saveForm () {
        this.editedItem.username = this.$store.state.user.name
        console.log('submit form: ', this.editedItem)
        if (this.validateAll()) return
        this.editedItem.id != null ? this.ubahItem(this.editedItem) : this.tambahItem(this.editedItem)
      },
      validateAll () {
        this.$v.$touch()
        this.errors = this.$v.editedItem.$anyError || this.$v.editedItem.$invalid
        var validate = !!this.errors
        return validate
      },
      close () {
        this.dialog = false
        this.$v.$reset()
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 300)
      },
      tambahItem (item) {
        this.editedItem.idPenelitian = this.$route.params.id
        apiServer
          .post('/tema', item)
          .then(response => {
            this.editedItem = this.defaultItem
            this.getData()
            this.showSnackbar('success', 'Tema berhasil ditambah')
            this.dialog = false
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar('error', 'Terdapat Kesalahan')
          })
      },
      ubahItem (item) {
        apiServer
          .put('/tema', item)
          .then(response => {
            this.editedItem = this.defaultItem
            this.getData()
            this.showSnackbar('success', 'Tema berhasil diubah')
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
      deleteItem (item) {
        confirm('Anda yakin menghapus ' + item.nama + '?') &&
          this.hapus(item)
      },
      hapus (item) {
        this.editedItem.username = this.$store.state.user.name
        apiServer
          .delete('/tema/' + item.id + '/' + this.editedItem.username)
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
