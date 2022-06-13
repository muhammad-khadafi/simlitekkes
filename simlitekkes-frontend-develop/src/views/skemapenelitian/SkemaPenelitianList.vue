<template>
  <v-container>
    <material-card v-if="hasAccess == false">
      <page403 />
    </material-card>
    <div v-if="hasAccess">
      <v-data-table
        :headers="headers"
        :items="listSkema"
        sort-by="namaKategori"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Skema Penelitian</v-toolbar-title>
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
          </v-toolbar>
        </template>
        <template v-slot:item.action="{ item }">
          <v-btn
            small
            color="blue"
            text
            @click="editItem(item)"
          >
            Ubah
          </v-btn>
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
  import { apiServer } from '../../setup-axios'

  export default {

    components: {
      Page403: () => import('@/views/403.vue'),
    },
    data: () => ({
      search: '',
      snackbar: false,
      snackbarText: '',
      snackbarColor: '',
      isLoading: true,
      menu: false,
      dialog: false,
      listSkema: [],
      headers: [
        {
          text: 'Nama Skema',
          align: 'left',
          sortable: false,
          value: 'nama',
        },
        { text: 'Kategori Penelitian', value: 'namaKategori' },
        { text: 'TKT', value: 'tktString' },
        { text: 'Jenis Penelitian', value: 'namaJenisPenelitian' },
        { text: 'Jumlah Dana', value: 'jumlahDanaString' },
        { text: 'Aksi', align: 'center', value: 'action', sortable: false },
      ],
      editedIndex: -1,
      luaran: '',
      hasAccess: null,
    }),

    created () {
      if (this.$store.state.user.organizationId != null) {
        this.getData()
      } else {
        this.hasAccess = false
      }
    },

    methods: {
      getData () {
        this.isLoading = true
        apiServer
          .get('/validasi/checkUserIsFromPpsdm/' + this.$store.state.user.organizationId)
          .then(response => {
            this.hasAccess = response.data
            apiServer
              .get('/skemaPenelitian')
              .then(response => {
                this.listSkema = response.data
                console.log('skemaPenelitian:', response.data) // For now, logs out the response
                this.isLoading = false
              })
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
      editItem (item) {
        this.$router.push('/skema-penelitian/' + item.id)
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

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }
</style>
