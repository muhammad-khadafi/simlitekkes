<template>
  <v-container
    class="fill-height"
    fluid
  >
    <v-row justify="center">
      <v-col cols="8">
        <material-card
          color="green"
          title="Tema dan Topik Unggulan Poltekkes"
        >
          <div v-if="hasAccess === true">
            <v-row justify="center">
              <v-col cols="10">
                <v-select
                  v-model="$v.idTema.$model"
                  :items="tema"
                  item-text="nama"
                  item-value="id"
                  label="Tema Unggulan*"
                  :error-messages="$v.idTema.$error ? ['Wajib Diisi'] : []"
                />
                <v-select
                  v-model="$v.idTopik.$model"
                  :items="topik"
                  item-text="nama"
                  item-value="id"
                  label="Topik Unggulan*"
                  :error-messages="$v.idTopik.$error ? ['Wajib Diisi'] : []"
                />
              </v-col>
            </v-row>
            <v-row justify="end">
              <v-col cols="2">
                <v-btn
                  color="green"
                  class="white--text"
                  @click="chooseTopik"
                >
                  Pilih
                </v-btn>
              </v-col>
            </v-row>
            <v-divider />
            <v-row justify="center">
              <v-col cols="10">
                <v-label class="headline">
                  Tema Unggulan
                </v-label>
              </v-col>
              <v-col cols="10">
                <strong><v-label class="headline">{{ organisasi.temaUnggulan }}</v-label></strong>
              </v-col>
            </v-row>
            <v-row justify="center">
              <v-col cols="10">
                <v-label class="headline">
                  Topik Unggulan
                </v-label>
              </v-col>
              <v-col cols="10">
                <strong><v-label class="headline">{{ organisasi.topikUnggulan }}</v-label></strong>
              </v-col>
            </v-row>
          </div>
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
  import { required } from 'vuelidate/lib/validators'
  import Page403 from '@/views/403.vue'

  export default {
    name: 'TopikUnggulan',
    components: { MaterialCard, Page403 },
    data: () => ({
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      tema: [],
      topik: [],
      idTema: '',
      idTopik: '',
      idUser: null,
      organisasi: {
        id: '',
        idTopik: '',
        idTema: '',
        temaUnggulan: '',
        topikUnggulan: '',
        username: '',
      },
      errors: false,
      hasAccess: null,
    }),

    watch: {
      idTema (val) {
        this.getTopik()
        this.idTopik = ''
      },
    },

    created () {
      this.checkValidasi()
    },

    validations: {
      idTema: { required },
      idTopik: { required },
    },

    methods: {
      checkValidasi () {
        var url = this.$store.state.user.role.roleCode === 'KAPUS_PPM' ? 'checkUserIsKapusPpm' : 'checkUserIsOperatorPpm'
        apiServer
          .get('/validasi/' + url + '/' + this.$store.state.user.userId + '/' + this.$store.state.user.role.roleCode)
          .then(response => {
            this.hasAccess = response.data
            if (this.hasAccess) {
              this.getTema()
              this.getData()
            }
          })
          .catch(error => {
            console.log('error when checkValidasi: ', error.response)
          })
      },
      getTema () {
        apiServer
          .get('/tema')
          .then(response => {
            this.tema = response.data
          })
          .catch(error => {
            console.log('there was an error: ' + error.response)
            this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
          })
      },
      getTopik () {
        apiServer
          .get('/topik/' + this.idTema)
          .then(response => {
            this.topik = response.data
          })
          .catch(error => {
            console.log('there was an error: ' + error.response)
            this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
          })
      },
      getData () {
        this.idUser = this.$store.state.user.userId
        apiServer
          .get('/topik-unggulan/' + this.idUser)
          .then(response => {
            this.organisasi = response.data
          })
          .catch(error => {
            console.log('there was an error: ' + error.response)
            this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
          })
      },
      chooseTopik () {
        this.$v.$touch()
        this.errors = this.$v.idTopik.$anyError
        this.organisasi.username = this.$store.state.user.name
        this.organisasi.idTopik = this.idTopik
        if (this.errors === false) {
          apiServer
            .put('/topik-unggulan', this.organisasi)
            .then(response => {
              this.getData()
              this.showSnackbar('success', 'Tema dan Topik Unggulan berhasil dipilih')
            })
            .catch(error => {
              console.log('there was an error: ' + error.response)
              this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
            })
        } else {
          this.showSnackbar('error', 'Harap perbaiki error terlebih dahulu')
        }
      },
      showSnackbar (color, message) {
        this.snackbar = true
        this.snackbarColor = color
        this.snackbarText = message
      },
    },
  }
</script>

<style>

</style>
