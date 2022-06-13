<template>
  <v-container fluid>
    <div v-if="hasAccess === false">
      <v-card>
        <v-toolbar>
          <v-toolbar-title>Penugasan Reviewer</v-toolbar-title>
        </v-toolbar>
        <v-row>
          <v-col class="align">
            <page403 />
          </v-col>
        </v-row>
      </v-card>
    </div>
    <div v-else>
      <reviewer-internal />
      <reviewer-eksternal />
    </div>
  </v-container>
</template>

<script>
  import ReviewerInternal from '@/views/penugasanreviewer/ReviewerInternal.vue'
  import ReviewerEksternal from '@/views/penugasanreviewer/ReviewerEksternal.vue'
  import { apiServer } from '../../setup-axios'
  import Page403 from '@/views/403.vue'

  export default {
    components: { ReviewerInternal, ReviewerEksternal, Page403 },
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

<style lang="scss">

</style>
