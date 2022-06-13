<template>
  <v-container fluid>
    <div v-if="hasAccess === true">
      <v-data-table
        :headers="headers"
        :items="organisasi"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Institusi</v-toolbar-title>
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
              color="green"
              class="white--text"
              @click="addOrganisasi"
            >
              Tambah
            </v-btn>
          </v-toolbar>
        </template>

        <template v-slot:item.action="{ item }">
          <v-btn
            small
            color="blue darken-1"
            text
            @click="editOrganisasi(item)"
          >
            Ubah
          </v-btn>
          <v-btn
            small
            color="blue darken-1"
            text
            @click="deleteOrganisasi(item)"
          >
            Hapus
          </v-btn>
        </template>

        <template v-slot:no-data />
      </v-data-table>
    </div>
    <div v-if="hasAccess === false">
      <v-card>
        <v-toolbar>
          <v-toolbar-title>Daftar Institusi</v-toolbar-title>
        </v-toolbar>
        <v-row>
          <v-col class="align">
            <page403 />
          </v-col>
        </v-row>
      </v-card>
    </div>
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
        Tutup
      </v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
  import { apiServer } from '../../setup-axios'
  import Page403 from '@/views/403.vue'

  export default {
    components: { Page403 },
    data: () => ({
      search: '',
      autoGrow: false,
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      editedIndex: -1,
      organisasi: [],
      headers: [
        { text: 'Nama', value: 'nama' },
        { text: 'No Telepon', value: 'noTelp' },
        { text: 'Email', value: 'email' },
        { text: 'Website', value: 'website' },
        { text: 'Aksi', value: 'action', sortable: false },
      ],
      hasAccess: null,
    }),

    created () {
      this.checkValidasi()
    },

    methods: {
      checkValidasi () {
        apiServer
          .get('/validasi/checkUserIsPengelolaPusat/' + this.$store.state.user.userId + '/' + this.$store.state.user.role.roleCode)
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
        if (this.$store.state.organisasi.snackbarText != null) {
          this.showMessage()
        }
        this.isLoading = true
        apiServer
          .get('/organisasi')
          .then(response => {
            this.organisasi = response.data
            this.isLoading = false
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
      addOrganisasi () {
        this.$router.push('/organisasi/0')
      },
      editOrganisasi (item) {
        this.$router.push('/organisasi/' + item.id)
      },
      deleteOrganisasi (item) {
        if (confirm('Apakah Anda yakin ingin menghapus data ' + item.nama + '?')) {
          apiServer
            .delete('/organisasi/' + item.id + '/' + this.$store.state.user.name)
            .then(response => {
              this.getData()
              this.showSnackbar('success', 'Data ' + item.nama + ' berhasil dihapus.')
            })
            .catch(error => {
              if (error.response.status === 409) {
                this.showSnackbar('error', 'Data ' + item.nama + ' tidak bisa dihapus karena digunakan oleh data lain!')
              } else {
                this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
              }
            })
        }
      },
      showSnackbar (color, message) {
        this.snackbar = true
        this.snackbarColor = color
        this.snackbarText = message
      },
      showMessage () {
        this.showSnackbar(
          this.$store.state.organisasi.snackbarColor,
          this.$store.state.organisasi.snackbarText
        )
        this.$store.dispatch('setOrganisasi', {
          snackbarColor: null,
          snackbarText: null,
        })
      },

    },

  }
</script>

<style>

</style>
