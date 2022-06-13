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
          type: 'bar',
        },
        title: {
          text: 'Proposal Per Tema',
        },
        xAxis: {
          categories: [],
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
        legend: {
          reversed: true,
        },
        plotOptions: {
          series: {
            stacking: 'normal',
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
          .get('/dashboard/proposal-per-tema/chart/' + this.$store.state.user.organizationId)
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
