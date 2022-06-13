<template>
  <v-container fluid>
    <material-card
      color="green"
      title="Riwayat Review"
    >
      <div v-if="hasAccess === false">
        <v-card>
          <v-row>
            <v-col class="align">
              <page403 />
            </v-col>
          </v-row>
        </v-card>
      </div>
      <div v-else>
        <v-data-table
          :headers="headers"
          :items="daftarReview"
          class="elevation-1"
          :loading="isLoading"
          loading-text="Loading... Please wait"
          :search="search"
        >
          <template v-slot:top>
            <v-toolbar dense>
              <v-toolbar-title>Daftar Review</v-toolbar-title>
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
            </v-toolbar>
          </template>
          <template v-slot:no-data />
        </v-data-table>
        <v-row
          justify="end"
          class="ml-12 mt-6"
        >
          <v-btn
            v-show="roleUser !== 'REVIEWER'"
            color="grey"
            class="mb-2 white--text mr-2"
            @click="back"
          >
            Kembali
          </v-btn>
        </v-row>
      </div>
    </material-card>
  </v-container>
</template>

<script>
  import MaterialCard from '../components/material/Card'
  import { apiServer } from '../setup-axios'
  import Page403 from '@/views/403.vue'

  export default {
    components: { MaterialCard, Page403 },
    data: () => ({
      headers: [
        { text: 'Tahun Usulan', value: 'tahunUsulan' },
        { text: 'Judul', width: '50%', value: 'judulPenelitian' },
        { text: 'Institusi', value: 'institusiKetuaTim' },
      ],
      daftarReview: [],
      isLoading: false,
      search: '',
      roleUser: null,
      hasAccess: null,
    }),

    created () {
      this.checkValidasi()
    },

    methods: {
      checkValidasi () {
        this.roleUser = this.$store.state.user.role.roleCode
        var url = ''
        if (this.roleUser === 'REVIEWER') {
          url = 'checkUserIsReviewer'
        } else if (this.roleUser === 'KAPUS_PPM') {
          url = 'checkUserIsKapusPpm'
        } else if (this.roleUser === 'PENGELOLA_PUSAT') {
          url = 'checkUserIsPengelolaPusat'
        } else {
          this.hasAccess = false
        }

        if (this.hasAccess === null) {
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
        }
      },
      getData () {
        var url = ''
        if (this.roleUser === 'REVIEWER') {
          url = '/riwayat-review/' + this.$store.state.user.userId
        } else {
          url = '/riwayat-review/' + this.$store.state.riwayatReview.tahunUsulan + '/' + this.$store.state.riwayatReview.idPeneliti
        }
        apiServer
          .get(url)
          .then(response => {
            this.daftarReview = response.data
            this.isLoading = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
      back () {
        this.$router.go(-1)
      },
    },
  }
</script>

<style>

</style>
