<template>
  <v-container fluid>
    <div v-if="hasAccess == true">
      <v-card raised>
        <v-tabs
          v-model="tab"
          background-color="primary"
          fixed-tabs
          dark
        >
          <v-tab :to="'/usulan'">
            Daftar Usulan
          </v-tab>
          <v-tab :to="'/riwayat-usulan'">
            Riwayat Usulan
          </v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item :id="tab">
            <router-view />
          </v-tab-item>
        </v-tabs-items>
      </v-card>
    </div>
    <div v-if="hasAccess == false">
      <v-card>
        <v-row>
          <v-col class="align">
            <page403 />
          </v-col>
        </v-row>
      </v-card>
    </div>
  </v-container>
</template>

<script>
  import { apiServer } from '../setup-axios'

  export default {

    components: {
      Page403: () => import('@/views/403.vue'),
    },
    data: () => ({
      tab: null,
      hasAccess: null,
    }),

    watch: {
      dialog (val) {
        val || this.close()
      },
    },

    created () {
      this.validasi()
    },

    methods: {
      validasi () {
        apiServer
          .get('/validasi/isPoltekkes/' + this.$store.state.user.organizationId)
          .then(response => {
            var isPoltekkes = response.data
            var isDosen = this.$store.state.user.role.roleCode === 'DOSEN'
            this.hasAccess = !!(isPoltekkes && isDosen)
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
    },
  }
</script>
