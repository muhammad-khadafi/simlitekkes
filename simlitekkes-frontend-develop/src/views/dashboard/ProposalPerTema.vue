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
            >Proposal per Tema:</span>
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
          <v-tab-item><proposal-per-tema-chart /></v-tab-item>
          <v-tab-item><proposal-per-tema-table /></v-tab-item>
        </v-tabs-items>
      </material-card>
    </div>
  </v-container>
</template>

<script>
  import MaterialCard from '../../components/material/Card'
  import ProposalPerTemaChart from '@/views/dashboard/ProposalPerTemaChart.vue'
  import ProposalPerTemaTable from '@/views/dashboard/ProposalPerTemaTable.vue'

  export default {
    components: { MaterialCard, ProposalPerTemaChart, ProposalPerTemaTable },
    data: () => ({
      tabs: null,

    }),
  }
</script>

<style>

</style>
