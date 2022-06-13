<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="userweb"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Pengguna</v-toolbar-title>
          </v-toolbar>
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
            <div class="flex-grow-1" />
            <v-dialog
              v-model="dialog"
              max-width="620px"
            >
              <template v-slot:activator="{ on }">
                <v-btn
                  color="blue"
                  dark
                  class="mb-2"
                  v-on="on"
                >
                  Tambah
                </v-btn>
              </template>

              <v-card>
                <v-card-title>
                  <span class="headline">{{ formTitle }}</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.username"
                          :error-messages="usernameErrors"
                          label="Username*"
                          required
                          :disabled="editedIndex > -1"
                          @input="$v.editedItem.username.$touch()"
                          @blur="$v.editedItem.username.$touch()"
                        />
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.nama"
                          label="Nama*"
                          required
                          :error-messages="namaErrors"
                          @input="$v.editedItem.nama.$touch()"
                          @blur="$v.editedItem.nama.$touch()"
                        />
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.nidn"
                          label="NIDN"
                          :error-messages="nidnErrors"
                          @input="$v.editedItem.nidn.$touch()"
                          @blur="$v.editedItem.nidn.$touch()"
                        />
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.nitk"
                          label="NITK"
                          :error-messages="nitkErrors"
                          @input="$v.editedItem.nitk.$touch()"
                          @blur="$v.editedItem.nitk.$touch()"
                        />
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.nip"
                          label="NIP"
                          :error-messages="nipErrors"
                          @input="$v.editedItem.nip.$touch()"
                          @blur="$v.editedItem.nip.$touch()"
                        />
                      </v-col>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.email"
                          label="Email*"
                          required
                          :error-messages="emailErrors"
                          @input="$v.editedItem.email.$touch()"
                          @blur="$v.editedItem.email.$touch()"
                        />
                      </v-col>

                      <v-col cols="8">
                        <v-file-input
                          v-model="fileFoto"
                          accept="image/png, image/jpeg, image/bmp"
                          prepend-icon="mdi-camera"
                          label="Foto"
                          @change="onFilePicked"
                        />
                      </v-col>

                      <v-col cols="4">
                        <v-img
                          :src="imageSrc"
                          height="200"
                          contain
                          class="grey darken-4"
                        />
                      </v-col>

                      <v-col cols="12">
                        <v-select
                          v-model="editedItem.idOrganisasi"
                          :items="organisasi"
                          item-text="nama"
                          item-value="id"
                          label="Institusi*"
                          required
                          :error-messages="organisasiErrors"
                          @input="$v.editedItem.idOrganisasi.$touch()"
                          @blur="$v.editedItem.idOrganisasi.$touch()"
                        />
                      </v-col>
                      <v-col cols="12">
                        <v-checkbox
                          v-model="editedItem.isActive"
                          label="Status Aktif"
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
                    :disabled="saveLock"
                    @click="save"
                  >
                    Simpan
                  </v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:item.isActive="{ item }">
          <v-icon>{{ item.isActive ? "mdi-check" : "mdi-minus" }}</v-icon>
        </template>

        <template v-slot:item.action="{ item }">
          <v-icon
            v-tooltip.top-center="editTip"
            small
            class="mr-2"
            @click="editItem(item)"
          >
            mdi-pencil
          </v-icon>
          <v-icon
            v-tooltip.top-center="deleteTip"
            small
            class="mr-2"
            @click="deleteItem(item)"
          >
            mdi-delete
          </v-icon>
          <v-icon
            v-if="item.isActive"
            v-tooltip.top-center="resetTip"
            small
            @click="resetPasswordItem(item)"
          >
            mdi-key
          </v-icon>
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
  import { required, email, maxLength, isEmailUnique } from 'vuelidate/lib/validators'

  export default {
    data: () => ({
      saveLock: false,
      editTip: 'Edit',
      deleteTip: 'Hapus',
      resetTip: 'Reset Password',
      search: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      menu: false,
      dialog: false,
      userweb: [],
      headers: [
        { text: 'Username', value: 'username' },
        { text: 'Nama', value: 'nama' },
        { text: 'NIP', value: 'nip' },
        { text: 'NITK', value: 'nitk' },
        { text: 'NIDN', value: 'nidn' },
        { text: 'Email', value: 'email' },
        { text: 'Institusi', value: 'organisasi' },
        { text: 'Aktif?', value: 'isActive' },
        { text: 'Aksi', value: 'action', sortable: false },
      ],
      editedIndex: -1,
      editedItem: {
        username: '',
        nama: '',
        nip: '',
        email: '',
        idOrganisasi: '',
        isActive: '',
        usernameSubmitter: '',
        fotoName: null,
        foto: null,
      },
      defaultItem: {
        username: '',
        nama: '',
        nip: '',
        email: '',
        idOrganisasi: '',
        isActive: '',
        usernameSubmitter: '',
        fotoName: null,
        foto: null,
      },
      organisasi: [],
      imageSrc: '',
      fileFoto: null,
      encodedFoto: '',
      usernameEdited: '',
      oldFoto: '',
      emailEdited: '',
    }),

    validations: {
      editedItem: {
        username: {
          required,
          maxLength: maxLength(100),
          async isUnique (value) {
            if (value === '') return true

            if (value === this.usernameEdited) return true

            for (var index in this.userweb) {
              if (
                this.userweb[index].username.toUpperCase().trim() ===
                value.toUpperCase().trim()
              ) {
                return false
              }
            }
            return true
          },
        },
        nama: {
          required,
          maxLength: maxLength(100),
        },
        nip: {
          maxLength: maxLength(25),
        },
        nidn: {
          maxLength: maxLength(25),
        },
        nitk: {
          maxLength: maxLength(25),
        },
        email: {
          required,
          email,
          maxLength: maxLength(100),
          isEmailUnique (value) {
            if (value === '') return true

            if (value === this.emailEdited) return true

            for (var index in this.userweb) {
              if (
                this.userweb[index].email.toUpperCase().trim() ===
                value.toUpperCase().trim()
              ) {
                return false
              }
            }
            return true
          },
        },
        idOrganisasi: {
          required,
        },
      },
    },

    computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'Tambah User' : 'Edit User'
      },
      usernameErrors () {
        const errors = []
        if (!this.$v.editedItem.username.$dirty) return errors
        !this.$v.editedItem.username.required &&
          errors.push('Username harus di isi.')
        !this.$v.editedItem.username.isUnique &&
          errors.push('Username sudah terdaftar')
        !this.$v.editedItem.username.maxLength &&
          errors.push('Panjang maksimal 100 karakter')
        return errors
      },
      namaErrors () {
        const errors = []
        if (!this.$v.editedItem.nama.$dirty) return errors
        !this.$v.editedItem.nama.required && errors.push('Nama harus di isi.')
        !this.$v.editedItem.nama.maxLength &&
          errors.push('Panjang maksimal 100 karakter')
        return errors
      },
      nipErrors () {
        const errors = []
        if (!this.$v.editedItem.nip.$dirty) return errors
        !this.$v.editedItem.nip.maxLength &&
          errors.push('Panjang maksimal 25 karakter')
        return errors
      },
      nidnErrors () {
        const errors = []
        if (!this.$v.editedItem.nidn.$dirty) return errors
        !this.$v.editedItem.nidn.maxLength &&
          errors.push('Panjang maksimal 25 karakter')
        return errors
      },
      nitkErrors () {
        const errors = []
        if (!this.$v.editedItem.nitk.$dirty) return errors
        !this.$v.editedItem.nitk.maxLength &&
          errors.push('Panjang maksimal 25 karakter')
        return errors
      },
      emailErrors () {
        const errors = []
        if (!this.$v.editedItem.email.$dirty) return errors
        !this.$v.editedItem.email.required && errors.push('Email harus di isi.')
        !this.$v.editedItem.email.maxLength &&
          errors.push('Panjang maksimal 100 karakter')
        !this.$v.editedItem.email.email &&
          errors.push('Gunakan format penulisan email yang benar')
        !this.$v.editedItem.email.isEmailUnique &&
          errors.push('Email sudah terdaftar')
        return errors
      },
      organisasiErrors () {
        const errors = []
        if (!this.$v.editedItem.idOrganisasi.$dirty) return errors
        !this.$v.editedItem.idOrganisasi.required &&
          errors.push('Organisasi harus di isi.')
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
      this.getOrganisasi()
    },

    methods: {
      getData () {
        this.isLoading = true
        console.log(this.isLoading)
        apiServer
          .get(
            '/userweb-all/' +
              this.$store.state.user.organizationId +
              '/' +
              this.$store.state.user.role.roleCode
          )
          .then(response => {
            this.userweb = response.data
            console.log('getData() ' + response.data) // For now, logs out the response
            this.isLoading = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
      getOrganisasi () {
        apiServer
          .get('/organisasi-list/' +
            this.$store.state.user.organizationId)
          .then(response => {
            this.organisasi = response.data
            console.log(response.data) // For now, logs out the response
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      getFoto () {
        apiServer.get('/userweb-foto/' + this.editedItem.id).then(response => {
          if (response.data != null) {
            console.log('Get Foto : ' + response.data)
            this.encodedFoto = response.data
            this.fileFoto = this.dataURLtoFile(
              this.encodedFoto,
              this.editedItem.fotoName
            )
            this.oldFoto = this.fileFoto
            const fileReader = new FileReader()
            fileReader.addEventListener('load', () => {
              this.imageSrc = fileReader.result
            })
            fileReader.readAsDataURL(this.fileFoto)
          }
        })
      },
      postData () {
        apiServer
          .post('/userweb', this.editedItem)
          .then(response => {
            if (this.fileFoto != null) {
              console.log('fileFoto at post ' + this.fileFoto)
              const formData = new FormData()
              formData.append('file', this.fileFoto)
              apiServer
                .post('/userweb-foto/' + response.data.id, formData)
                .then(response => {
                  this.getData()
                  this.showSnackbar('success', 'Berhasil menambah user')
                  this.close()
                })
                .catch(error => {
                  this.close()
                  console.log('There was an error:', error.response)
                  this.showSnackbar(
                    'error',
                    'Failed to store photo, Silakan hubungi administrator anda!'
                  )
                })
            } else {
              this.close()
              this.getData()
              this.showSnackbar('success', 'Berhasil menambah user')
            }
          })
          .catch(error => {
            this.close()
            console.log('There was an error:', error.response)
            this.showSnackbar(
              'error',
              'Error, Silakan hubungi administrator anda!'
            )
          })
      },
      deleteData (id) {
        apiServer
          .delete('/userweb-foto/' + id)
          .then(response => {
            apiServer
              .delete('/userweb/' + id + '/' + this.$store.state.user.name)
              .then(response => {
                this.getData()
                this.showSnackbar('success', 'Berhasil menghapus data')
                this.close()
              })
              .catch(error => {
                this.close()
                console.log('There was an error:', error.response)
                this.showSnackbar('error', error.response.data)
              })
          })
          .catch(error => {
            console.log(error.response)
            this.close()
            this.showSnackbar(
              'error',
              'Error, silakan kontak administrator anda!'
            )
          })
      },
      updateData () {
        apiServer
          .put('/userweb', this.editedItem)
          .then(response => {
            console.log('fasd', this.fileFoto != this.oldFoto)
            if (this.fileFoto != this.oldFoto) {
              apiServer
                .delete('/userweb-foto/' + this.editedItem.id)
                .then(response => {
                  if (this.fileFoto != null) {
                    console.log('fileFoto at put ' + this.fileFoto)
                    const formData = new FormData()
                    formData.append('file', this.fileFoto)
                    apiServer
                      .post('/userweb-foto/' + this.editedItem.id, formData)
                      .then(response => {
                        this.getData()
                        this.showSnackbar('success', 'Berhasil edit data')
                        this.close()
                      })
                      .catch(error => {
                        this.close()
                        console.log('There was an error:', error.response)
                        this.showSnackbar(
                          'error',
                          'Gagal update foto, silakan hubungi administrator anda'
                        )
                      })
                  } else {
                    this.close()
                    this.getData()
                    this.showSnackbar('success', 'Berhasil edit data')
                  }
                })
                .catch(error => {
                  console.log(error.response)
                  this.close()
                  this.showSnackbar(
                    'error',
                    'Error, silakan kontak administrator anda!'
                  )
                })
            } else {
              this.close()
              this.getData()
              this.showSnackbar('success', 'Berhasil edit data')
            }
          })
          .catch(error => {
            this.close()
            console.log('There was an error:', error.response)
            this.showSnackbar(
              'error',
              'Error, Silakan hubungi administrator anda!'
            )
          })
      },
      resetPassword (item) {
        apiServer
          .put('/userweb/reset-password', item)
          .then(response => {
            this.getData()
            this.showSnackbar('success', 'Sukses melakukan reset password')
          })
          .catch(error => {
            console.log('There was an error:', error.response)
            this.showSnackbar(
              'error',
              'Error, Silakan hubungi administrator anda!'
            )
          })
      },
      editItem (item) {
        console.log('foto at update : ', this.editedItem.fotoName)
        this.editedIndex = this.userweb.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.usernameEdited = item.username
        this.emailEdited = item.email
        this.dialog = true
        if (this.editedItem.fotoName != null) {
          this.getFoto()
        } else {
          this.resetFoto()
        }
      },
      deleteItem (item) {
        if (item.id === this.$store.state.user.userId) {
          this.showSnackbar('error', 'Tidak bisa menghapus diri sendiri')
        } else {
          confirm('Apakah anda yakin akan menghapus data ini?') &&
            this.deleteData(item.id)
        }
      },
      resetPasswordItem (item) {
        confirm(
          'Apakah anda yakin akan mereset password untuk user ' +
            item.username +
            '?'
        ) && this.resetPassword(item)
      },
      close () {
        this.$v.$reset()
        this.resetFoto()
        this.usernameEdited = ''
        this.emailEdited = ''
        this.dialog = false
        this.saveLock = false
        setTimeout(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        }, 300)
      },
      save () {
        this.$v.$touch()
        if (!this.$v.$invalid) {
          this.editedItem.usernameSubmitter = this.$store.state.user.name
          this.saveLock = true
          if (this.editedIndex > -1) {
            this.updateData()
          } else {
            this.postData()
          }
        }
      },
      resetFoto () {
        console.log('RESET FOTO')
        this.imageSrc = ''
        this.fileFoto = null
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },

      onFilePicked (event) {
        console.log('fileFoto at picked ' + this.fileFoto)
        if (this.fileFoto != null) {
          const fileReader = new FileReader()
          fileReader.addEventListener('load', () => {
            this.imageSrc = fileReader.result
          })
          fileReader.readAsDataURL(event)
          console.log('DEBUG [imgSrc] : ', this.imageSrc)
          console.log('DEBUG [fileFoto] : ', this.fileFoto)
        } else {
          this.resetFoto()
        }
      },
      dataURLtoFile (dataurl, filename) {
        console.log('DATA URL ' + dataurl)
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
    },
  }
</script>
