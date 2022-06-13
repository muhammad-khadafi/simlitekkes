<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col cols="8">
        <material-card
          color="green"
          :title="formTitle"
        >
          <v-form>
            <v-row>
              <v-col cols="12">
                <v-row>
                  <v-col cols="6">
                    <v-text-field
                      v-model="$v.organisasi.nama.$model"
                      label="Nama Institusi*"
                      counter="100"
                      :error-messages="namaErrors"
                    />
                  </v-col>
                  <v-col cols="6">
                    <v-textarea
                      v-model="$v.organisasi.alamat.$model"
                      label="Alamat Institusi"
                      :auto-grow="autoGrow"
                      rows="1"
                      counter="100"
                      :error-messages="$v.organisasi.alamat.$error ? ['Isian tidak boleh melebihi dari 100 karakter'] : []"
                    />
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="6">
                    <v-text-field
                      v-model="$v.organisasi.noTelp.$model"
                      label="No. Telepon"
                      counter="20"
                      :error-messages="$v.organisasi.noTelp.$error ? ['Isian tidak boleh melebihi dari 20 karakter'] : []"
                    />
                  </v-col>
                  <v-col cols="6">
                    <v-text-field
                      v-model="$v.organisasi.noFax.$model"
                      label="No. Fax"
                      counter="20"
                      :error-messages="$v.organisasi.noFax.$error ? ['Isian tidak boleh melebihi dari 20 karakter'] : []"
                    />
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="6">
                    <v-text-field
                      v-model.lazy="$v.organisasi.email.$model"
                      label="Email"
                      counter="50"
                      :error-messages="emailErrors"
                    />
                  </v-col>
                  <v-col cols="6">
                    <v-text-field
                      v-model="$v.organisasi.website.$model"
                      label="Website"
                      counter="250"
                      :error-messages="$v.organisasi.website.$error ? ['Isian tidak boleh melebihi dari 250 karakter'] : []"
                    />
                  </v-col>
                </v-row>
                <v-row>
                  <v-col cols="12">
                    <v-switch
                      v-model="organisasi.isPoltekkes"
                      label="Poltekkes"
                    />
                  </v-col>
                </v-row>
              </v-col>
            </v-row>

            <v-row
              justify="end"
              class="ml-12 mt-6"
            >
              <v-btn
                color="red"
                class="mb-2 white--text mr-2"
                @click="cancel"
              >
                Batal
              </v-btn>
              <v-btn
                color="blue"
                class="mb-2 white--text mr-2"
                @click="save"
              >
                Simpan
              </v-btn>
            </v-row>
          </v-form>
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
  import MaterialCard from '../../components/material/Card'
  import { required, email, maxLength } from 'vuelidate/lib/validators'
  import { apiServer } from '../../setup-axios'

  export default {
    name: 'OrganisasiAddUpdate',
    components: { MaterialCard },
    data: () => ({
      autoGrow: true,
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      errors: false,
      idOrganisasi: null,
      organisasi: {
        id: null,
        nama: '',
        alamat: '',
        noTelp: '',
        noFax: '',
        email: '',
        website: '',
        isPoltekkes: false,
        username: '',
      },

    }),

    validations: {
      organisasi: {
        nama: { required, maxLength: maxLength(100) },
        alamat: { maxLength: maxLength(100) },
        noTelp: { maxLength: maxLength(20) },
        noFax: { maxLength: maxLength(20) },
        email: { email, maxLength: maxLength(50) },
        website: { maxLength: maxLength(250) },
      },

    },

    computed: {
      formTitle () {
        return this.idOrganisasi === '0' ? 'Tambah Institusi' : 'Ubah Institusi'
      },
      namaErrors () {
        const errorsForNama = []
        if (!this.$v.organisasi.nama.$dirty) {
          return errorsForNama
        }

        if (!this.$v.organisasi.nama.required) {
          errorsForNama.push('Wajib diisi')
        }

        if (!this.$v.organisasi.nama.maxLength) {
          errorsForNama.push('Isian tidak boleh melebihi dari 100 karakter')
        }

        return errorsForNama
      },
      emailErrors () {
        const errorsForEmail = []
        if (!this.$v.organisasi.email.$dirty) {
          return errorsForEmail
        }

        if (!this.$v.organisasi.email.email) {
          errorsForEmail.push('Isi dengan format email yang valid')
        }

        if (!this.$v.organisasi.email.maxLength) {
          errorsForEmail.push('Isian tidak boleh melebihi dari 50 karakter')
        }

        return errorsForEmail
      },
    },

    created () {
      this.idOrganisasi = this.$route.params.id
      if (this.idOrganisasi !== '0') {
        this.getData()
      }
    },

    methods: {
      getData () {
        apiServer
          .get('/organisasi/' + this.idOrganisasi)
          .then(response => {
            this.organisasi = response.data
          })
          .catch(error => {
            console.log('there was an error:', error)
            this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
          })
      },
      cancel () {
        this.$router.replace('/organisasi')
      },
      save () {
        this.$v.$touch()
        this.errors = this.$v.organisasi.$anyError
        this.organisasi.username = this.$store.state.user.name

        if (this.idOrganisasi === '0') {
          this.addOrganisasi()
        } else {
          this.updateOrganisasi()
        }
      },
      addOrganisasi () {
        if (this.errors === false) {
          apiServer
            .post('/organisasi', this.organisasi)
            .then(response => {
              this.$store.dispatch('setOrganisasi', {
                snackbarColor: 'success',
                snackbarText: 'Institusi berhasil ditambah',
              })
              // this.showSnackbar('success', 'Institusi berhasil ditambah')
              this.$router.replace('/organisasi')
            })
            .catch(error => {
              if (error.response.status === 409) {
                this.showSnackbar('error', this.organisasi.nama + ' sudah terdaftar.')
              } else {
                this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
              }
            })
        } else {
          this.showSnackbar('error', 'Harap perbaiki error terlebih dahulu')
        }
      },
      updateOrganisasi () {
        if (this.errors === false) {
          apiServer
            .put('/organisasi', this.organisasi)
            .then(response => {
              this.$store.dispatch('setOrganisasi', {
                snackbarColor: 'success',
                snackbarText: 'Institusi berhasil diubah',
              })
              // this.showSnackbar('success', 'Institusi berhasil diubah')
              this.$router.replace('/organisasi')
            })
            .catch(error => {
              if (error.response.status === 409) {
                this.showSnackbar('error', this.organisasi.nama + ' sudah terdaftar.')
              } else {
                this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
              }
            })
        } else {
          this.showSnackbar('error', 'Harap perbaiki error terlebih dahulu')
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
