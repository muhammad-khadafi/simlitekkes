<template>
  <v-container class="dashboard" fluid>
    <div v-if="hasAccess === false">
      <v-card>
        <v-toolbar>
          <v-toolbar-title>Dashboard</v-toolbar-title>
        </v-toolbar>
        <v-row>
          <v-col class="align">
            <page403 />
          </v-col>
        </v-row>
      </v-card>
    </div>
    <div v-else>
      <v-row class="color">
        <v-col
          cols="12"
          lg="12"
        >
          <proposal-per-skema />
        </v-col>
        <v-col
          cols="12"
          lg="12"
        >
          <proposal-per-kelompok-tkt />
        </v-col>
        <v-col
          cols="12"
          lg="12"
        >
          <proposal-per-tema />
        </v-col>
        <v-col
          cols="12"
          lg="12"
        >
          <material-card
            class="card-tabs"
            color="green"
            title="Jumlah Proposal per Topik"
          >
            <proposal-per-topik />
          </material-card>
        </v-col>
        <v-col
          cols="12"
          lg="6"
        >
          <peneliti-per-pendidikan-terakhir />
        </v-col>
        <v-col
          cols="12"
          lg="6"
        >
          <material-card
            class="card-tabs"
            color="green"
            title="Jumlah Peneliti dan Reviewer per Bidang Ilmu"
          >
            <peneliti-reviewer-per-bidang-ilmu />
          </material-card>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>

<script>
  import MaterialCard from '../../components/material/Card'
  import { apiServer } from '../../setup-axios'
  import Page403 from '@/views/403.vue'
  import PenelitiPerPendidikanTerakhir from '@/views/dashboard/PenelitiPerPendidikanTerakhir.vue'
  import PenelitiReviewerPerBidangIlmu from '@/views/dashboard/PenelitiReviewerPerBidangIlmu.vue'
  import ProposalPerSkema from '@/views/dashboard/ProposalPerSkema.vue'
  import ProposalPerKelompokTkt from '@/views/dashboard/ProposalPerKelompokTkt.vue'
  import ProposalPerTopik from '@/views/dashboard/ProposalPerTopik.vue'
  import ProposalPerTema from '@/views/dashboard/ProposalPerTema.vue'

  export default {
    components: {
      MaterialCard,
      Page403,
      PenelitiPerPendidikanTerakhir,
      PenelitiReviewerPerBidangIlmu,
      ProposalPerSkema,
      ProposalPerKelompokTkt,
      ProposalPerTopik,
      ProposalPerTema,
    },
    data: () => ({
      hasAccess: null,
    }),

    created () {
      this.checkValidasi()
    },

    methods: {
      checkValidasi () {
        var url = this.$store.state.user.role.roleCode === 'KAPUS_PPM' ? 'checkUserIsKapusPpm' : 'checkUserIsPengelolaPusat'
        apiServer
          .get('/validasi/' + url + '/' + this.$store.state.user.userId + '/' + this.$store.state.user.role.roleCode)
          .then(response => {
            this.hasAccess = response.data
          })
          .catch(error => {
            console.log('error when checkValidasi: ', error.response)
          })
      },
    },

  }
</script>

<style>
  .dashboard{
    background-color: white !important;
  }
</style>
