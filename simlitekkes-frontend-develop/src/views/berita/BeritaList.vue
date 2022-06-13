<template>
  <v-container>
    <div v-if="isAdmin">
      <berita-list-for-admin />
    </div>
    <div v-else>
      <berita-list-for-non-admin />
    </div>
  </v-container>
</template>

<script>
  import BeritaListForAdmin from '@/views/berita/BeritaListForAdmin.vue'
  import BeritaListForNonAdmin from '@/views/berita/BeritaListForNonAdmin.vue'

  export default {
    components: { BeritaListForAdmin, BeritaListForNonAdmin },
    data: () => ({
      isAdmin: null,
      role: null,
      idOrganisasi: null,
    }),

    created () {
      this.role = this.$store.state.user.role.roleCode
      this.idOrganisasi = this.$store.state.user.organizationId
      if (this.role === 'ADMINISTRATOR' || this.role === 'PENGELOLA_PUSAT') {
        if (this.idOrganisasi === 1) {
          this.isAdmin = true
        } else {
          this.isAdmin = false
        }
      } else if (this.role === 'OPERATOR_PPM') {
        if (this.idOrganisasi !== 1) {
          this.isAdmin = true
        } else {
          this.isAdmin = false
        }
      } else {
        this.isAdmin = false
      }
    },

    methods: {

    },

  }
</script>

<style>

</style>
