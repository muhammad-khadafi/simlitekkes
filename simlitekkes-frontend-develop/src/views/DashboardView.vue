<template>
<v-app>
  <v-content>
    <v-app style="background-color: #eee;">
      <core-app-bar />

      <core-drawer />

      <!-- <v-fade-transition mode="out-in">
        <router-view />
      </v-fade-transition> -->
      <core-view />

      <core-footer />

    </v-app>
  </v-content>
</v-app>
</template>

<script>
  export default {
    components: {
      CoreDrawer: () => import('@/components/core/Drawer'),
      CoreAppBar: () => import('@/components/core/AppBar'),
      CoreView: () => import('@/components/core/View'),
      CoreToolbar: () => import('@/components/core/Toolbar'),
      CoreFooter: () => import('@/components/core/Footer')
    }
  }
</script>
