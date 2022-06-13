<template>
  <v-expansion-panels
    :disabled="!listTahun.length"
  >
    <v-expansion-panel v-if="listTahun.length == 0 && isLoadFinished == true">
      <v-expansion-panel-header>
        <h1 class="body-1 text--primary">
          Tidak ada data jadwal kegiatan
        </h1>
      </v-expansion-panel-header>
    </v-expansion-panel>
    <v-expansion-panel
      v-for="(item,i) in listTahun"
      :key="i"
    >
      <v-expansion-panel-header>
        <h1 class="body-1 text--primary">
          Tahun {{ item }}
        </h1>
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        <v-data-table
          :headers="headers"
          :items="listRencanaKegiatanYearly[i]"
          sort-by="judul"
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
          <template v-slot:no-data />
        </v-data-table>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
  import { apiServer } from '../../../../setup-axios'
  import axios from 'axios'
  import moment from 'moment'

  export default {
    data: () => ({
      search: '',
      isLoading: true,
      listRencanaKegiatanYearly: [],
      listTahun: [],
      skemaPenelitian: {},
      dropdownTahun: [],
      headers: [
        {
          text: 'Nama Kegiatan',
          align: 'center',
          value: 'namaKegiatan',
        },
        {
          text: 'Tanggal Mulai',
          align: 'center',
          value: 'tanggalAwalJadwalF',
        },
        {
          text: 'Tanggal Selesai',
          align: 'center',
          value: 'tanggalAkhirJadwalF',
        },
        {
          text: 'Tahun Kegiatan',
          align: 'center',
          value: 'tahun',
        },
      ],
      isLoadFinished: false,
    }),

    created () {
      this.getData()
    },

    methods: {
      getData () {
        this.isLoading = true
        console.log(this.isLoading)
        this.listTahun = []
        this.listRencanaKegiatanYearly = []
        axios.all([
          apiServer.get('/jadwal-kegiatan/tahun/' + this.$route.params.id),
          apiServer.get('/penelitian/' + this.$route.params.id),
        ]).then(axios.spread((resp1, resp2) => {
          this.listTahun = resp1.data
          console.log('list tahun:', resp1.data) // For now, logs out the response
          for (var i = 0; i < this.listTahun.length; i++) {
            apiServer
              .get('/jadwal-kegiatan/' + this.$route.params.id + '/' + this.listTahun[i])
              .then(response => {
                this.listRencanaKegiatanYearly.push(response.data)
                response.data.forEach(this.formattingDate)
              })
          }
          this.isLoading = false
          this.fillDropdownTahun(resp2.data)
        })).catch(error => {
          console.log('Terdapat error:', error.response) // Logs out the error
        })
      },
      fillDropdownTahun (item) {
        for (var i = item.tahunMulaiPelaksanaan; i <= item.tahunSelesaiPelaksanaan; i++) {
          this.dropdownTahun.push({ text: i, value: parseInt(i) })
        }
        this.isLoadFinished = true
      },
      formattingDate (value) {
        value.tanggalAwalJadwalF = moment(value.tanggalAwalJadwal).format('DD-MM-YYYY')
        value.tanggalAwalJadwal = value.tanggalAwalJadwal
          ? moment(value.tanggalAwalJadwal).format('YYYY-MM-DD')
          : ''
        value.tanggalAkhirJadwalF = moment(value.tanggalAkhirJadwal).format('DD-MM-YYYY')
        value.tanggalAkhirJadwal = value.tanggalAkhirJadwal
          ? moment(value.tanggalAkhirJadwal).format('YYYY-MM-DD')
          : ''
      },
    },
  }
</script>

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }
</style>
