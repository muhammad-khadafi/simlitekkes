<template>
  <v-container fluid>
    <v-card class="mx-auto mb-4">
      <v-card-text
        v-show="isNotif"
        class="error font-weight-bold"
      >
        {{ notifMessage }}
      </v-card-text>
    </v-card>
    <v-expansion-panels
      v-model="panel"
      multiple
    >
      <v-expansion-panel
        v-for="(rab, index) in rabList"
        :key="index"
      >
        <v-expansion-panel-header>
          Tahun {{ rab.tahun }}
          <div class="flex-grow-1" />
          Total Biaya : {{ rab.totalBiaya }}
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-data-table
            :headers="headers"
            :items="rab.rencanaAnggaranListPerYear"
            class="elevation-1"
            :loading="isLoading"
            loading-text="Loading... Please wait"
          >
          </v-data-table>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-container>
</template>

<script>
  import { apiServer } from '../../../../setup-axios'
  import { required, numeric } from 'vuelidate/lib/validators'

  export default {
    data: () => ({
      panel: [],
      readonly: false,
      rabList: [],
      jenisPembelanjaan: [],
      isLoading: true,
      cost: 1700000,
      headers: [
        { text: 'Komponen RAB', value: 'namaKomponen', sortable: false },
        { text: 'Item', value: 'item', sortable: false },
        { text: 'Satuan', value: 'satuan', sortable: false },
        { text: 'Volume', value: 'volume', sortable: false },
        { text: 'Biaya Satuan', value: 'biayaSatuanFormatted', sortable: false },
        { text: 'Total', value: 'subTotalFormatted', sortable: false },
      ],
      dataTahun: [],
      editedIndex: -1,
      biayaTotal: '',
      isNotif: false,
      notifMessage: '',
      totalRabAllYears: 0,
      minBudget: 0,
      maxBudget: 0,
    }),

    validations: {
      editedItem: {
        idKomponenRab: {
          required,
        },
        item: {
          required,
        },
        volume: {
          required,
          numeric,
        },
        satuan: {
          required,
        },
        biayaSatuan: {
          required,
          numeric,
        },
      },
    },

    created () {
      this.getData()
      this.getJenisPembelanjaan()
      this.getBudgetInfo()
    },

    methods: {
      getBudgetInfo () {
        apiServer
          .get('/dana-anggaran/' + this.$route.params.id)
          .then(response => {
            console.log('dana-anggaran ', response.data)
            this.minBudget = response.data.jumlahDanaMin
            this.maxBudget = response.data.jumlahDanaMax

            console.log('dana-anggaran ', this.minBudget)
          })
          .catch(error => {
            console.log('There was an error:', error)
          })
      },
      getData () {
        this.isLoading = true
        apiServer
          .get('/rencana-anggaran/' + this.$route.params.id)
          .then(response => {
            this.rabList = response.data
            console.log('rab list : ', response.data)
            this.totalRabAllYears = 0
            response.data.forEach(this.accumulateAllYears)
            this.validationRabAllYears()
            this.openingPanel()
          })
          .catch(error => {
            console.log('There was an error:', error) // Logs out the error
          })
        this.isLoading = false
      },
      accumulateAllYears (data) {
        this.totalRabAllYears = this.totalRabAllYears + data.totalBiayaNum
      },
      openingPanel () {
        let i
        for (i = 0; i < this.rabList.length; i++) {
          this.panel.push(i)
        }
      },
      validationRabAllYears () {
        console.log(
          'Total rab all years : ' +
            new Intl.NumberFormat('id-ID', {
              style: 'currency',
              currency: 'IDR',
            }).format(this.totalRabAllYears)
        )
        console.log(
          'Dana Minimum : ' +
            new Intl.NumberFormat('id-ID', {
              style: 'currency',
              currency: 'IDR',
            }).format(this.minBudget)
        )
        console.log(
          'Dana maksimum : ' +
            new Intl.NumberFormat('id-ID', {
              style: 'currency',
              currency: 'IDR',
            }).format(this.maxBudget)
        )
        if (this.totalRabAllYears < this.minBudget) {
          this.isNotif = true
          this.notifMessage = 'Jumlah rab semua tahun kurang dari dana minimum'
        } else if (this.totalRabAllYears > this.maxBudget) {
          this.isNotif = true
          this.notifMessage = 'Jumlah rab semua tahun lebih dari dana maksimum'
        } else {
          this.isNotif = false
        }
      },
      showSnackbar (color, message) {
        console.log('snack ' + color + ' - ' + message)
        this.snackbar = true
        this.snackbarText = message
        this.snackbarColor = color
      },
      getJenisPembelanjaan () {
        apiServer
          .get('/komponen-rab')
          .then(response => {
            console.log('result getjenispembelanjaan:', response.data)
            this.jenisPembelanjaan = response.data
          })
          .catch(error => {
            console.log('There was an error:', error) // Logs out the error
          })
      },
    },
  }
</script>

<style lang="scss" scoped>
  #tblPokok td {
    border: 1px dotted black;
  }
</style>
