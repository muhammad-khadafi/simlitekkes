<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="listDosen"
        sort-by="nidn"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Peneliti</v-toolbar-title>
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
            <v-btn
              color="blue"
              class="white--text"
              @click="addDosen"
            >
              Tambah Peneliti
            </v-btn>
            <v-dialog
              v-model="dialog"
              max-width="500px"
            >
              <v-card>
                <v-card-title class="mb-1">
                  <span class="headline">{{ editedItem.isNotExist ? 'Tambah Akun Belum Terdaftar' : 'Tambah Akun Terdaftar' }}</span>
                </v-card-title>
                <v-card-subtitle>Nama Peneliti: {{ dosen.nama }} </v-card-subtitle>

                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12">
                        <v-autocomplete
                          v-if="!editedItem.isNotExist"
                          v-model="editedItem.idUser"
                          label="Pilih Username*"
                          :items="listUsername"
                          :loading="loadingUsername"
                          hide-no-data
                          item-text="username"
                          item-value="id"
                          clearable
                          :error-messages="errors.idUser"
                        >
                          <template
                            slot="selection"
                            slot-scope="data"
                          >
                            {{ data.item.username }}
                          </template>
                        </v-autocomplete>
                        <v-text-field
                          v-if="editedItem.isNotExist"
                          v-model="editedItem.username"
                          label="Ketikkan Username"
                          :error-messages="errors.username"
                        />
                      </v-col>
                      <v-col cols="12">
                        <v-checkbox
                          v-model="editedItem.isNotExist"
                          label="Belum Terdaftar?"
                        />
                      </v-col>
                    </v-row>
                  </v-container>

                  <v-card-actions>
                    <div class="flex-grow-1" />
                    <v-btn
                      color="blue darken-1 white--text"
                      @click="save"
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
                </v-card-text>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>
        <template v-slot:item.action="{ item }">
          <v-btn
            v-if="isRoleOpPpm"
            small
            color="blue darken-1"
            text
            :disabled="item.idUser !== null"
            @click="tambahAkun(item)"
          >
            Tambah Akun
          </v-btn>
          <v-btn
            small
            color="blue darken-1"
            text
            @click="detailDosen(item)"
          >
            Detail
          </v-btn>
        </template>
        <template v-slot:no-data />
      </v-data-table>

      <v-snackbar
        v-model="snackbar"
        :color="snackbarColor"
        :timeout="4000"
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
  import { apiServer } from '../../setup-axios'
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
      listDosen: [],
      headers: [
        {
          text: 'NIDN',
          align: 'left',
          value: 'nidn',
        },
        {
          text: 'Nama',
          align: 'left',
          value: 'nama',
        },
        {
          text: 'Institusi',
          align: 'left',
          value: 'namaInstitusi',
        },
        {
          text: 'Jabatan Akademik',
          align: 'left',
          value: 'jabatanAkademikFungsional',
        },
        {
          text: 'Status',
          align: 'left',
          value: 'isActiveStr',
        },
        {
          text: 'Username',
          align: 'left',
          value: 'namaUser',
        },
        { text: 'Aksi', value: 'action', align: 'center', sortable: false },
      ],
      idOrganisasi: null,
      isRoleOpPpm: null,
      editedItem: {
        idUser: null,
        username: null,
        isNotExist: false,
      },
      defaultItem: {
        idUser: null,
        username: null,
        isNotExist: false,
      },
      loadingUsername: false,
      listUsername: [],
      dosen: {},
      errors: {
        idUser: [],
        username: [],
      },
    }),

    watch: {
      dialog (val) {
        val || this.close()
      },
    },

    created () {
      this.getData()
    },

    methods: {
      getData () {
        this.isLoading = true
        if ((this.$store.state.user.role.roleName).includes('PPM')) {
          var role = this.$store.state.user.role.roleCode
          this.isRoleOpPpm = role === 'OPERATOR_PPM'
          this.idOrganisasi = this.$store.state.user.organizationId
          axios.all([
            apiServer.get('/peneliti/organisasi/' + this.idOrganisasi),
            apiServer.get('/userweb/isNotPeneliti/' + this.idOrganisasi),
          ]).then(axios.spread((response, response1) => {
            this.listDosen = response.data
            this.listUsername = response1.data
            this.isLoading = false
          })).catch(error => {
            console.log('Terdapat error:', error.response) // Logs out the error
            this.isLoading = false
          })
        } else {
          apiServer
            .get('/peneliti')
            .then(response => {
              this.listDosen = response.data
              this.isLoading = false
            })
            .catch(error => {
              console.log('There was an error:', error.response) // Logs out the error
              this.isLoading = false
            })
        }
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
      detailDosen (item) {
        this.$router.push('/dosen/' + item.id)
      },
      addDosen () {
        this.$router.push('/dosen/0')
      },
      tambahAkun (item) {
        this.clearError()
        this.dosen = item
        this.dialog = true
      },
      close () {
        this.dialog = false
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.dosen = {}
        }, 300)
      },
      clearError () {
        this.errors.username = []
        this.errors.idUser = []
      },
      save () {
        if (this.dosen.email === null || this.dosen.email === '') {
          this.showSnackbar('error', 'Harap mengisi email pada data non pokok peneliti ' + this.dosen.nama)
          this.dialog = false
          return
        }
        axios.all([
          apiServer.get('/userweb/checkIsDosen/' + this.dosen.id),
          apiServer.get('/role/roleCode/' + 'DOSEN'),
        ]).then(axios.spread((response, response1) => {
          this.clearError()
          var hasRoleDosen = response.data
          var role = response1.data
          if (this.editedItem.isNotExist) {
            if (this.editedItem.username === null) {
              this.errors.username.push('Data tidak boleh kosong')
            } else if (this.dosen.email === null || this.dosen.email === '') {
              this.errors.username.push('Harap mengisi email pada data non pokok peneliti')
            } else {
              apiServer
                .get('/validasi/checkUsernameIsUnique/' + this.editedItem.username)
                .then(response => {
                  if (response.data === true) {
                    this.editedItem.nama = this.dosen.nama
                    this.editedItem.email = this.dosen.email
                    this.editedItem.isActive = this.dosen.isActive
                    this.editedItem.nidn = this.dosen.nidn
                    this.editedItem.idOrganisasi = this.dosen.idOrganisasi
                    apiServer
                      .post('/userweb', this.editedItem)
                      .then(response => {
                        var userweb = response.data
                        var userRole = {}
                        userRole.username = this.$store.state.user.name
                        userRole.idRoles = []
                        userRole.idRoles.push(role.id)
                        userRole.idUserweb = userweb.id
                        this.dosen.idUser = userweb.id
                        axios.all([
                          apiServer.post('/user-role', userRole),
                          apiServer.put('/peneliti', this.dosen),
                        ]).then(axios.spread((resp, resp1) => {
                          this.showSnackbar('success', 'Tambah akun pada peneliti berhasil, cek email untuk melihat password')
                          this.dialog = false
                          this.getData()
                        }))
                      })
                  } else {
                    this.errors.username.push('Username ini sudah terpakai, coba ketik username lain')
                  }
                })
            }
          } else {
            if (this.editedItem.idUser === null) {
              this.errors.idUser.push('Data tidak boleh kosong')
              return
            }
            this.dosen.idUser = this.editedItem.idUser
            apiServer
              .put('/peneliti', this.dosen)
              .then(response => {
                if (!hasRoleDosen) {
                  var userRole = {}
                  userRole.username = this.$store.state.user.name
                  userRole.idRoles = []
                  userRole.idRoles.push(role.id)
                  userRole.idUserweb = this.editedItem.idUser
                  apiServer
                    .post('/user-role', userRole)
                    .then(response => {
                      apiServer
                        .get('/userweb/id/' + userRole.idUserweb)
                        .then(response => {
                          var userweb = response.data
                          userweb.email = this.dosen.email
                          apiServer
                            .put('/userweb', userweb)
                            .then(response => {
                              this.showSnackbar('success', 'Tambah akun pada peneliti berhasil')
                              this.dialog = false
                              this.getData()
                            })
                        })
                    })
                } else {
                  this.showSnackbar('success', 'Tambah akun pada peneliti berhasil')
                  this.dialog = false
                }
              })
          }
        })).catch(error => {
          console.log('Terdapat error:', error.response) // Logs out the error
          this.showSnackbar('error', 'Penambahan akun pada peneliti gagal')
          this.dialog = false
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
