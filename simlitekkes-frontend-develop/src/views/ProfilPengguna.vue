<template>
  <v-container
    class="fill-height"
    fluid
  >
    <v-row justify="center">
      <v-col
        cols="12"
        md="8"
      >
        <material-card
          color="green"
          title="Ubah Profil"
        >
          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="$v.user.nama.$model"
                label="Nama*"
                :error-messages="namaErrors"
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="user.nidn"
                label="NIDN"
                :hint="role === 'DOSEN' ? 'tidak otomatis mengubah NIDN di peneliti' : ''"
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="user.nitk"
                label="NITK"
                :hint="role === 'DOSEN' ? 'tidak otomatis mengubah NITK di peneliti' : ''"
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="user.nip"
                label="NIP"
                :hint="role === 'DOSEN' ? 'tidak otomatis mengubah NIP di peneliti' : ''"
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="$v.user.email.$model"
                label="Email*"
                :error-messages="emailErrors"
              />
            </v-col>
          </v-row>
          <v-row justify="center">
            <div class="flex-grow-1" />
            <v-col cols="3">
              <v-btn
                color="blue"
                class="white--text"
                @click="updateProfil"
              >
                Ubah Profil
              </v-btn>
            </v-col>
          </v-row>
        </material-card>
      </v-col>
      <v-col
        cols="12"
        md="4"
      >
        <material-card class="v-card-profile">
          <v-avatar
            size="130"
            class="mx-auto d-block elevation-6"
          >
            <v-img
              v-if="imageSrc !== ''"
              :src="imageSrc"
            />
            <v-img
              v-else
              src="../assets/avatar.svg"
            />
          </v-avatar>

          <v-card-text>
            <v-file-input
              v-model="fileFoto"
              accept="image/png, image/jpeg, image/bmp"
              prepend-icon="mdi-camera"
              label="Foto"
              @change="fotoChanged"
            />
          </v-card-text>
          <v-card-text class="text-center">
            <v-btn
              color="blue"
              class="white--text"
              @click="updateFoto"
            >
              Ubah Foto
            </v-btn>
          </v-card-text>
        </material-card>
      </v-col>
    </v-row>
    <v-snackbar
      v-model="snackbar"
      :color="snackbarColor"
      :timeout="3000"
      :top="true"
    >
      {{ snackbarText }}
      <v-btn
        dark
        text
        @click="snackbar = false"
      >
        Tutup
      </v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
  import { apiServer } from '../setup-axios'
  import { required, email, maxLength } from 'vuelidate/lib/validators'

  export default {
    data: () => ({
      user: {
        id: '',
        nama: '',
        nip: '',
        nidn: '',
        nitk: '',
        email: '',
        fotoName: '',
        foto: null,
        username: '',
      },
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      imageSrc: '',
      fileFoto: null,
      encodedFoto: '',
      errors: false,
      role: null,
    }),

    validations: {
      user: {
        nama: { required, maxLength: maxLength(100) },
        email: { email, maxLength: maxLength(100) },
      },
    },

    computed: {
      namaErrors () {
        const errorsForNama = []
        if (!this.$v.user.nama.$dirty) {
          return errorsForNama
        }

        if (!this.$v.user.nama.required) {
          errorsForNama.push('Wajib diisi')
        }

        if (!this.$v.user.nama.maxLength) {
          errorsForNama.push('Isian tidak boleh melebihi dari 100 karakter')
        }

        return errorsForNama
      },
      emailErrors () {
        const errorsForEmail = []
        if (!this.$v.user.email.$dirty) {
          return errorsForEmail
        }

        if (!this.$v.user.email.email) {
          errorsForEmail.push('Isi dengan format email yang valid')
        }

        if (!this.$v.user.email.maxLength) {
          errorsForEmail.push('Isian tidak boleh melebihi dari 50 karakter')
        }

        return errorsForEmail
      },
    },

    created () {
      this.role = this.$store.state.user.role.roleCode
      this.getData()
    },

    methods: {
      getData () {
        apiServer
          .get('/userweb-profil/' + this.$store.state.user.userId)
          .then(response => {
            this.user = response.data
            if (this.user.fotoName !== null) {
              this.getFoto()
            } else {
              this.resetFoto()
            }
          })
          .catch(error => {
            console.log('There was an error: ', error.response) // Logs out the error
            this.showSnackbar('error', 'Error, please contact your administrator!')
          })
      },
      getFoto () {
        apiServer.get('/userweb-foto/' + this.$store.state.user.userId)
          .then(response => {
            if (response.data != null) {
              this.encodedFoto = response.data
              this.fileFoto = this.dataURLtoFile(
                this.encodedFoto,
                this.user.fotoName
              )
              const fileReader = new FileReader()
              fileReader.addEventListener('load', () => {
                this.imageSrc = fileReader.result
              })
              fileReader.readAsDataURL(this.fileFoto)
            }
          })
      },
      fotoChanged (event) {
        if (this.fileFoto != null) {
          const fileReader = new FileReader()
          fileReader.addEventListener('load', () => {
            this.imageSrc = fileReader.result
          })
        } else {
          this.resetFoto()
        }
      },
      resetFoto () {
        this.imageSrc = ''
        this.fileFoto = null
      },
      dataURLtoFile (dataurl, filename) {
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
      updateProfil () {
        this.$v.$touch()
        this.errors = this.$v.user.$anyError
        this.user.username = this.$store.state.user.name
        if (this.errors === false) {
          apiServer
            .put('/userweb-profil', this.user)
            .then(response => {
              this.getData()
              this.showSnackbar('success', 'Profil Pengguna berhasil diubah')
            })
            .catch(error => {
              console.log('error at updateProfil:', error.response)
              this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
            })
        } else {
          this.showSnackbar('error', 'Harap perbaiki error terlebih dahulu')
        }
      },
      updateFoto () {
        console.log('isi fileFoto:', this.fileFoto)
        if (this.fileFoto != null) {
          const formData = new FormData()
          formData.append('file', this.fileFoto)
          apiServer
            .post('/userweb-foto/' + this.$store.state.user.userId, formData)
            .then(response => {
              this.getData()
              this.showSnackbar('success', 'Foto berhasil diubah')
            })
            .catch(error => {
              console.log('There was an error:', error.response)
              this.showSnackbar('error', 'Failed to store photo, Silakan hubungi administrator anda!')
            })
        } else {
          apiServer
            .delete('/userweb-foto/' + this.$store.state.user.userId)
            .then(response => {
              this.getData()
              this.showSnackbar('success', 'Foto berhasil diubah')
            })
            .catch(error => {
              console.log('error at deleteFoto:', error.response)
              this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
            })
        }
      },
      showSnackbar (color, message) {
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
    },

  }
</script>

<style>

</style>
