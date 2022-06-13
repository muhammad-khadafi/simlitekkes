<template>
  <v-container
    class="pt-10 pb-10"
  >
    <v-card>
      <v-tabs
        v-model="tab"
        background-color="primary"
        dark
      >
        <v-tab
          v-for="item in items"
          :to="item.to"
          :key="item.to"
          :disabled="!((hasAccess) || (!hasAccess && item.to === '/profil-dosen')) || (item.to === '/sinta-dosen')"
        >
          {{ item.tab }}
        </v-tab>
      </v-tabs>

      <v-tabs-items v-model="tab">
        <v-tab-item
          v-if="(hasAccess) || (!hasAccess && tab === '/profil-dosen')"
          :id="tab"
        >
          <router-view />
        </v-tab-item>
        <v-tab-item
          v-if="(hasAccess == false && tab !== '/profil-dosen')"
          :id="tab"
        >
          <page403 />
        </v-tab-item>
      </v-tabs-items>
    </v-card>
  </v-container>
</template>

<script>
  import { apiServer } from '../../../setup-axios'
  import axios from 'axios'

  export default {
    data: () => ({
      tab: null,
      hasAccess: null,
      items: [
        { tab: 'Profil', to: '/profil-dosen' },
        { tab: 'Sinta', to: '/sinta-dosen' },
        { tab: 'Penelitian', to: '/penelitian-dosen' },
        { tab: 'Jurnal', to: '/jurnal-dosen' },
        { tab: 'HKI', to: '/hki-dosen' },
        { tab: 'Prosiding', to: '/prosiding-dosen' },
        { tab: 'Buku', to: '/buku-dosen' },
      ],
    }),
    components: {
      Page403: () => import('@/views/403.vue'),
    },
    created () {
      axios.all([
        apiServer.get('/validasi/isPoltekkes/' + this.$store.state.user.organizationId),
        apiServer.get('/peneliti/user/' + this.$store.state.user.userId),
      ]).then(axios.spread((response, response1) => {
        var isPoltekkes = response.data
        var isDosen = this.$store.state.user.role.roleCode === 'DOSEN'
        var dosen = response1.data
        this.hasAccess = !!(isPoltekkes && isDosen && dosen.id !== null)
      })).catch(error => {
        console.log('There was an error:', error.response) // Logs out the error
      })
    },
  }
</script>

<style lang="scss">
  // .search{
  //   margin-left: 20%;
  // }
</style>
