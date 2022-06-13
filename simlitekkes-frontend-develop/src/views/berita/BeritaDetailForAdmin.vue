<template>
  <v-container fluid>
    <material-card
      color="green"
      title="Detail Berita"
    >
      <v-row justify="center">
        <v-col
          cols="12"
          md="8"
        >
          <v-text-field
            v-model="berita.namaOrganisasi"
            label="Institusi"
            readonly
          />
        </v-col>
        <v-col
          cols="12"
          md="8"
        >
          <v-text-field
            v-model="berita.namaUser"
            label="Diterbitkan Oleh"
            readonly
          />
        </v-col>
        <v-col
          cols="12"
          md="8"
        >
          <v-text-field
            v-model="berita.publishTimeStr"
            label="Waktu"
            readonly
          />
        </v-col>
        <v-col
          cols="12"
          md="8"
        >
          <v-text-field
            v-model="berita.judul"
            label="Judul"
            readonly
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
            readonly
          />
        </v-col>
        <v-col
          cols="12"
          md="8"
        >
          <v-checkbox
            v-model="berita.isPublished"
            label="Terbitkan?"
            readonly
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
          @click="back"
        >
          Kembali ke Daftar Berita
        </v-btn>
      </v-row>
    </material-card>
  </v-container>
</template>

<script>
  import { apiServer } from '../../setup-axios'

  export default {
    data: () => ({
      idBerita: '',
      berita: {
        id: '',
        idOrganisasi: '',
        namaOrganisasi: '',
        judul: '',
        isi: '',
        isPublished: true,
        userPublish: '',
        namaUser: '',
        publishTimeStr: '',
      },
      autoGrow: true,
    }),

    created () {
      this.idBerita = this.$route.params.id
      this.getData()
    },

    methods: {
      back () {
        this.$router.replace('/berita')
      },
      getData () {
        apiServer
          .get('/berita/detail/' + this.idBerita)
          .then(response => {
            this.berita = response.data
          })
          .catch(error => {
            console.log('error at getData for Berita:', error)
            this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
          })
      },
    },
  }
</script>

<style>

</style>
