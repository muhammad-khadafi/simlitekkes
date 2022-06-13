<template>
  <div>
    <highcharts :options="chartOptions" />
  </div>
</template>

<script>
  // eslint-disable-next-line no-unused-vars
  import Highcharts from 'highcharts'
  import { Chart } from 'highcharts-vue'
  import { apiServer } from '../../setup-axios'

  export default {
    components: { highcharts: Chart },
    data: () => ({
      chartOptions: {
        chart: {
          type: 'pie',
          reflow: true,
        },
        title: {
          text: 'Peneliti Per Pendidikan Terakhir',
        },
        tooltip: {
          pointFormat: '{series.name}: <b>{point.y}</b>',
        },
        plotOptions: {
          pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
              enabled: true,
              format: '<b>{point.name}</b>: {point.y}',
            },
          },
        },
        series: [{
          name: 'Jumlah',
          colorByPoint: true,
          data: [{
            name: 'Kosong',
            y: 1,
          }],
        }],
      },
    }),

    created () {
      this.getData()
    },

    methods: {
      getData () {
        apiServer
          .get('/dashboard/peneliti-per-pendidikan-terakhir/' + this.$store.state.user.organizationId)
          .then(response => {
            this.chartOptions.series[0].data = response.data
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
            this.isLoading = false
          })
      },
    },
  }
</script>

<style>

</style>
