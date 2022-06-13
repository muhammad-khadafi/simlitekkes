<template>
  <v-container fluid>
    <div>
      <material-card
        class="card-tabs"
        color="green"
      >
        <template v-slot:header>
          <v-tabs
            v-model="tabs"
            background-color="transparent"
          >
            <span
              class="subheading font-weight-light mx-3"
              style="align-self: left"
            >Peneliti per Pendidikan Terakhir:</span>
            <v-tab class="mr-3">
              <v-icon class="mr-2">
                mdi-chart-bar
              </v-icon>
              Chart
            </v-tab>
            <v-tab class="mr-3">
              <v-icon class="mr-2">
                mdi-table
              </v-icon>
              Table
            </v-tab>
          </v-tabs>
        </template>
        <v-tabs-items v-model="tabs">
          <v-tab-item><peneliti-per-pendidikan-terakhir-chart /></v-tab-item>
          <v-tab-item><peneliti-per-pendidikan-terakhir-table /></v-tab-item>
        </v-tabs-items>
      </material-card>
    </div>
  </v-container>
</template>

<script>
  import MaterialCard from '../../components/material/Card'
  import PenelitiPerPendidikanTerakhirChart from '@/views/dashboard/PenelitiPerPendidikanTerakhirChart.vue'
  import PenelitiPerPendidikanTerakhirTable from '@/views/dashboard/PenelitiPerPendidikanTerakhirTable.vue'

  export default {
    components: { MaterialCard, PenelitiPerPendidikanTerakhirChart, PenelitiPerPendidikanTerakhirTable },
    data: () => ({
      tabs: null,

    }),

  }
</script>

<style>

</style>
