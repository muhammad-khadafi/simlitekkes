<template>
  <v-data-table
    :headers="headers"
    :items="listMitraPenelitian"
    sort-by="namaMitra"
    class="elevation-1"
    :loading="isLoading"
    loading-text="Loading... Please wait"
    :search="search"
  >
    <template v-slot:top>
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
      </v-toolbar>
    </template>
    <template
      v-slot:item.actionDownload="{ item }"
    >
      <v-btn
        small
        color="blue darken-1"
        text
        :disabled="item.filePath == null || item.filePath == ''"
        @click="downloadItem(item)"
      >
        Download
      </v-btn>
    </template>
    <template v-slot:item.listKontri="{ item }">
      <div
        v-for="kontri in item.listKontri"
        :key="kontri.tahun"
      >
        <div v-if="kontri.barangKontribusi">
          {{ kontri.tahun }} : {{ kontri.barangKontribusi }}
        </div>
        <div v-if="!kontri.barangKontribusi">
          {{ kontri.tahun }} : -
        </div>
      </div>
      <div v-if="item.listKontri.length < 1">
        -
      </div>
    </template>
    <template v-slot:no-data />
    <template v-slot:item.listKontri2="{ item }">
      <div
        v-for="kontri in item.listKontri"
        :key="kontri.tahun"
      >
        <div v-if="kontri.jumlahKontribusi">
          {{ kontri.tahun }} : {{ thousandSeparator(kontri.jumlahKontribusi) }}
        </div>
        <div v-if="!kontri.jumlahKontribusi">
          {{ kontri.tahun }} : -
        </div>
      </div>
      <div v-if="item.listKontri.length < 1">
        -
      </div>
    </template>
    <template v-slot:no-data />
  </v-data-table>
</template>

<script>
  import { apiServer, fileUrl } from '../../../../setup-axios'
  import axios from 'axios'

  export default {
    data: () => ({
      search: '',
      isLoading: true,
      menu: false,
      dialog: false,
      listMitraPenelitian: [],
      headers: [
        {
          text: 'Nama Mitra',
          align: 'center',
          value: 'namaMitra',
        },
        {
          text: 'Jenis Mitra',
          align: 'center',
          value: 'jenisMitraStr',
        },
        {
          text: 'Email',
          align: 'center',
          value: 'email',
        },
        {
          text: 'Penanggung Jawab',
          align: 'center',
          value: 'penanggungJawab',
        },
        {
          text: 'Jumlah Kontribusi',
          align: 'center',
          value: 'jumlahKontribusiStr',
        },
        {
          text: 'Detail Per Tahun',
          align: 'center',
          value: 'listKontri2',
        },
        {
          text: 'Barang Kontribusi',
          align: 'center',
          value: 'listKontri',
        },
        { text: 'MOU', value: 'actionDownload', align: 'center', sortable: false },
      ],
    }),

    watch: {
      dialog (val) {
        val || this.close()
      },
    },

    created () {
      this.getData()
    },

    methods: {
      resetData () {
        this.listMitraPenelitian = []
      },
      getData () {
        this.isLoading = true
        console.log(this.isLoading)
        this.resetData()
        axios.all([
          apiServer.get('/mitra-penelitian/' + this.$route.params.id + '/' + this.$store.state.user.organizationId + '/' + this.$store.state.user.role.roleCode + '/' + this.$store.state.user.userId),
        ]).then(axios.spread((resp) => {
          this.listMitraPenelitian = resp.data
          this.isLoading = false
        })).catch(error => {
          console.log('Terdapat error:', error.response) // Logs out the error
        })
      },
      downloadItem (item) {
        apiServer.post('/mitra-penelitian/download/' + item.id)
          .then((resp) => {
            const ticket = resp.data.ticket
            window.location = fileUrl + '/download/mitra-penelitian/' + item.id + '?ticket=' + ticket
          })
      },
      thousandSeparator (amount) {
        if (amount !== '' || amount !== undefined || amount !== 0 || amount !== '0' || amount !== null) {
          return amount.toString().replace(/\./g, '.').replace(/\B(?=(\d{3})+(?!\d))/g, '.')
        } else {
          return amount
        }
      },
    },
  }
</script>

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }
</style>
