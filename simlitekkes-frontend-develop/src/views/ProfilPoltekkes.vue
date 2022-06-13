<template>
  <v-container
    class="fill-height"
    fluid
  >
    <v-row justify="center">
      <v-col cols="10">
        <material-card
          color="green"
          title="Profil Poltekkes"
          text="Lengkapi Profil Poltekkes Anda"
        >
          <v-form v-if="hasAccess === true">
            <v-row>
              <v-col cols="6">
                <h2>Profil Poltekkes</h2>
                <v-text-field
                  v-model="$v.organisasi.noSkPendirian.$model"
                  label="No. SK Pendirian"
                  counter="100"
                  :error-messages="$v.organisasi.noSkPendirian.$error ? ['Isian tidak boleh melebihi dari 100 karakter'] : []"
                />
                <v-text-field
                  v-model="$v.organisasi.nama.$model"
                  label="Nama Lembaga*"
                  counter="100"
                  :error-messages="namaErrors"
                />
                <v-textarea
                  v-model="$v.organisasi.alamat.$model"
                  label="Alamat Lembaga"
                  :auto-grow="autoGrow"
                  rows="1"
                  counter="100"
                  :error-messages="$v.organisasi.alamat.$error ? ['Isian tidak boleh melebihi dari 100 karakter'] : []"
                />
                <v-text-field
                  v-model="$v.organisasi.noTelp.$model"
                  label="No. Telepon"
                  counter="20"
                  :error-messages="$v.organisasi.noTelp.$error ? ['Isian tidak boleh melebihi dari 20 karakter'] : []"
                />
                <v-text-field
                  v-model="$v.organisasi.noFax.$model"
                  label="No. Fax"
                  counter="20"
                  :error-messages="$v.organisasi.noFax.$error ? ['Isian tidak boleh melebihi dari 20 karakter'] : []"
                />
                <v-text-field
                  v-model.lazy="$v.organisasi.email.$model"
                  label="Email"
                  counter="50"
                  :error-messages="emailErrors"
                />
                <v-text-field
                  v-model="$v.organisasi.website.$model"
                  label="Website"
                  counter="250"
                  :error-messages="$v.organisasi.website.$error ? ['Isian tidak boleh melebihi dari 250 karakter'] : []"
                />
                <v-text-field
                  v-model="$v.organisasi.klasifikasi.$model"
                  label="Kelas*"
                  type="number"
                  :error-messages="$v.organisasi.klasifikasi.$error ? ['Wajib Diisi'] : []"
                />
              </v-col>

              <v-col cols="6">
                <h2>Pimpinan Poltekkes</h2>
                <v-text-field
                  v-model="$v.organisasi.namaJabatanKepala.$model"
                  label="Nama Jabatan"
                  counter="100"
                  :error-messages="$v.organisasi.namaJabatanKepala.$error ? ['Isian tidak boleh melebihi dari 100 karakter'] : []"
                />
                <v-text-field
                  v-model="$v.organisasi.nidnPejabatKepala.$model"
                  label="NIDN"
                  counter="100"
                  :error-messages="$v.organisasi.nidnPejabatKepala.$error ? ['Isian tidak boleh melebihi dari 100 karakter'] : []"
                />
                <v-text-field
                  v-model="$v.organisasi.namaPejabatKepala.$model"
                  label="Nama"
                  counter="100"
                  :error-messages="$v.organisasi.namaPejabatKepala.$error ? ['Isian tidak boleh melebihi dari 100 karakter'] : []"
                />
                <v-select
                  v-model="organisasi.jenisKelamin"
                  label="Jenis Kelamin"
                  :items="jenisKelamin"
                  item-text="jenisKelaminTxt"
                  item-value="jenisKelaminValue"
                />
                <v-text-field
                  v-model="$v.organisasi.jenjangPendidikan.$model"
                  label="Jenjang Pendidikan"
                  counter="100"
                  :error-messages="$v.organisasi.jenjangPendidikan.$error ? ['Isian tidak boleh melebihi dari 100 karakter'] : []"
                />
              </v-col>
            </v-row>

            <v-row justify="end">
              <v-col cols="2">
                <v-btn
                  color="blue"
                  class="white--text"
                  @click="updateData"
                >
                  Ubah Profil
                </v-btn>
              </v-col>
            </v-row>
          </v-form>
          <div v-if="hasAccess === false">
            <v-row>
              <v-col class="align">
                <page403 />
              </v-col>
            </v-row>
          </div>
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
  import MaterialCard from '../components/material/Card'
  import { apiServer } from '../setup-axios'
  import { required, email, maxLength } from 'vuelidate/lib/validators'
  import Page403 from '@/views/403.vue'

  export default {
    name: 'ProfilPoltekkes',
    components: { MaterialCard, Page403 },
    data: () => ({
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      errors: false,
      autoGrow: true,
      userId: '',
      organisasi: {
        id: '',
        noSkPendirian: '',
        nama: '',
        alamat: '',
        noTelp: '',
        noFax: '',
        email: '',
        website: '',
        namaJabatanKepala: '',
        nidnPejabatKepala: '',
        namaPejabatKepala: '',
        jenisKelamin: '',
        jenjangPendidikan: '',
        klasifikasi: '',
        username: '',
      },
      jenisKelamin: [
        { jenisKelaminTxt: 'Laki-laki', jenisKelaminValue: 'L' },
        { jenisKelaminTxt: 'Perempuan', jenisKelaminValue: 'P' },
      ],
      hasAccess: null,
    }),

    validations: {
      organisasi: {
        noSkPendirian: { maxLength: maxLength(100) },
        nama: { required, maxLength: maxLength(100) },
        alamat: { maxLength: maxLength(100) },
        noTelp: { maxLength: maxLength(20) },
        noFax: { maxLength: maxLength(20) },
        email: { email, maxLength: maxLength(50) },
        website: { maxLength: maxLength(250) },
        klasifikasi: { required },
        namaJabatanKepala: { maxLength: maxLength(100) },
        nidnPejabatKepala: { maxLength: maxLength(100) },
        namaPejabatKepala: { maxLength: maxLength(100) },
        jenjangPendidikan: { maxLength: maxLength(100) },
      },
    },

    computed: {
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
      this.checkValidasi()
    },

    methods: {
      checkValidasi () {
        var url = this.$store.state.user.role.roleCode === 'KAPUS_PPM' ? 'checkUserIsKapusPpm' : 'checkUserIsOperatorPpm'
        apiServer
          .get('/validasi/' + url + '/' + this.$store.state.user.userId + '/' + this.$store.state.user.role.roleCode)
          .then(response => {
            this.hasAccess = response.data
            if (this.hasAccess) {
              this.getData()
            }
          })
          .catch(error => {
            console.log('error when checkValidasi: ', error.response)
          })
      },
      getData () {
        this.userId = this.$store.state.user.userId
        apiServer
          .get('/profil-poltekkes/' + this.$store.state.user.userId)
          .then(response => {
            this.organisasi = response.data
          })
          .catch(error => {
            console.log('There was an error: ', error.response) // Logs out the error
            this.showSnackbar(
              'error',
              'Error, please contact your administrator!'
            )
          })
      },
      updateData () {
        this.$v.$touch()
        this.errors = this.$v.organisasi.$anyError
        this.organisasi.username = this.$store.state.user.name
        console.log(this.errors)
        if (this.errors === false) {
          apiServer
            .put('/profil-poltekkes', this.organisasi)
            .then(response => {
              this.getData()
              this.showSnackbar('success', 'Profil Poltekkes berhasil diubah')
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
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
    },

  }
</script>

<style scoped>

</style>
