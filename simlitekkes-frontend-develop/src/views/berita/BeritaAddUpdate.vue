<template>
  <v-container fluid>
    <material-card
      color="green"
      :title="formTitle"
    >
      <v-row justify="center">
        <v-col
          cols="12"
          md="8"
        >
          <v-autocomplete
            v-if="isAdminPpsdm"
            v-model="$v.berita.idOrganisasi.$model"
            label="Institusi*"
            :items="daftarOrganisasi"
            :loading="loadingOrganisasi"
            hide-no-data
            item-text="nama"
            item-value="id"
            clearable
            :error-messages="organisasiErrors"
          >
            <template
              slot="selection"
              slot-scope="data"
            >
              {{ data.item.nama }}
            </template>
          </v-autocomplete>
        </v-col>
        <v-col
          cols="12"
          md="8"
        >
          <v-text-field
            v-model="$v.berita.judul.$model"
            label="Judul*"
            :error-messages="judulErrors"
          />
        </v-col>
        <v-col
          cols="12"
          md="8"
        >
          <v-textarea
            v-model="berita.isi"
            label="Isi berita"
            :auto-grow="autoGrow"
            rows="10"
            outlined
          />
        </v-col>
        <v-col
          cols="12"
          md="8"
        >
          <v-checkbox
            v-model="berita.isPublished"
            label="Terbitkan?"
          />
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
    </material-card>
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
  import { apiServer } from '../../setup-axios'
  import { required, maxLength } from 'vuelidate/lib/validators'

  export default {
    data: () => ({
      idBerita: '',
      daftarOrganisasi: [],
      loadingOrganisasi: false,
      berita: {
        id: '',
        idOrganisasi: '',
        judul: '',
        isi: '',
        isPublished: true,
        userPublish: '',
      },
      isAdminPpsdm: null,
      autoGrow: true,
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      errors: false,
      namaOrganisasi: '',
    }),

    validations: {
      berita: {
        idOrganisasi: { required },
        judul: { required, maxLength: maxLength(250) },
      },

    },
    computed: {
      formTitle () {
        return this.idBerita === '0' ? 'Tambah Berita' : 'Ubah Berita'
      },
      organisasiErrors () {
        const errorsForOrganisasi = []
        if (!this.$v.berita.idOrganisasi.$dirty) {
          return errorsForOrganisasi
        }

        if (!this.$v.berita.idOrganisasi.required) {
          errorsForOrganisasi.push('Wajib diisi')
        }

        return errorsForOrganisasi
      },
      judulErrors () {
        const errorsForJudul = []
        if (!this.$v.berita.judul.$dirty) {
          return errorsForJudul
        }

        if (!this.$v.berita.judul.required) {
          errorsForJudul.push('Wajib diisi')
        }

        if (!this.$v.berita.judul.maxLength) {
          errorsForJudul.push('Isian tidak boleh melebihi dari 250 karakter')
        }

        return errorsForJudul
      },
    },

    created () {
      this.idBerita = this.$route.params.id
      if (this.idBerita !== '0') {
        this.getData()
      }

      if (this.$store.state.user.organizationId === 1 && this.$store.state.user.role.roleCode === 'ADMINISTRATOR') {
        this.isAdminPpsdm = true
        this.getAllOrganisasi()
      } else {
        this.isAdminPpsdm = false
        this.berita.idOrganisasi = this.$store.state.user.organizationId
      }
    },

    methods: {
      getData () {
        apiServer
          .get('/berita/' + this.idBerita)
          .then(response => {
            this.berita = response.data
          })
          .catch(error => {
            console.log('error at getData for Berita:', error)
            this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
          })
      },
      getAllOrganisasi () {
        this.loadingOrganisasi = true
        apiServer
          .get('/organisasi')
          .then(response => {
            this.daftarOrganisasi = response.data
            this.loadingOrganisasi = false
          })
          .catch(error => {
            console.log('error at getAllOrganisasi for Berita:', error.response) // Logs out the error
            this.loadingOrganisasi = false
          })
      },
      save () {
        this.$v.$touch()
        this.errors = this.$v.berita.$anyError
        this.berita.userPublish = this.$store.state.user.name

        if (this.errors === false) {
          if (this.idBerita === '0') {
            this.addBerita()
          } else {
            this.updateBerita()
          }
        } else {
          this.showSnackbar('error', 'Harap perbaiki error terlebih dahulu')
        }
      },
      cancel () {
        this.$router.replace('/berita')
      },
      addBerita () {
        apiServer
          .post('/berita', this.berita)
          .then(response => {
            this.$store.dispatch('setBerita', {
              snackbarColor: 'success',
              snackbarText: 'Berita berhasil ditambah',
            })
            this.$router.replace('/berita')
          })
          .catch(error => {
            console.log('error at addBerita:', error.response)
            this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
          })
      },
      updateBerita () {
        apiServer
          .put('/berita', this.berita)
          .then(response => {
            this.$store.dispatch('setBerita', {
              snackbarColor: 'success',
              snackbarText: 'Berita berhasil diubah',
            })

            this.$router.replace('/berita')
          })
          .catch(error => {
            console.log('error at updateBerita:', error.response)
            this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
          })
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
