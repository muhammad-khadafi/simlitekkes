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
          type: 'column',
        },
        title: {
          text: 'Proposal Per Skema',
        },
        tooltip: {
          headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
          pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y}</b></td></tr>',
          footerFormat: '</table>',
          shared: true,
          useHTML: true,
        },
        plotOptions: {
          column: {
            pointPadding: 0.2,
            borderWidth: 0,
          },
        },
        xAxis: {
          categories: [],
          crosshair: true,
          labels: {
            overflow: 'justify',
          },
        },
        yAxis: {
          min: 0,
          title: {
            text: 'Jumlah',
          },
        },
        series: [],
      },
    }),

    created () {
      this.getData()
    },

    methods: {
      getData () {
        apiServer
          .get('/dashboard/proposal-per-skema/chart/' + this.$store.state.user.organizationId)
          .then(response => {
            this.chartOptions.xAxis.categories = response.data.categories
            this.chartOptions.series = response.data.series
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
