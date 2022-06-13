<template>
  <v-container fluid>
    <v-row>
      <v-col cols="12">
        <v-data-iterator
          :items="items"
          :items-per-page.sync="itemsPerPage"
          :page="page"
          :search="search"
          :sort-by="sortBy.toLowerCase()"
          :sort-desc="sortDesc"
          hide-default-footer
        >
          <template v-slot:header>
            <v-toolbar
              dark
              color="green"
              class="mb-1"
            >
              <v-text-field
                v-model="search"
                clearable
                flat
                solo-inverted
                hide-details
                label="Cari"
              />
            </v-toolbar>
          </template>

          <template v-slot:default="props">
            <v-row
              v-for="berita in props.items"
              :key="berita.id"
              justify="center"
            >
              <v-col
                cols="11"
              >
                <v-card
                  class="mx-auto"
                >
                  <v-card-title>
                    <v-icon
                      large
                      left
                    >
                      mdi-newspaper-variant-outline
                    </v-icon>
                  </v-card-title>
                  <v-card-subtitle class="font-weight-bold">
                    {{ berita.judul }}
                  </v-card-subtitle>
                  <v-card-text>
                    {{ berita.isi }}
                    <span class="font-weight-bold"><a @click="viewBerita(berita)">selanjutnya ...</a></span>
                  </v-card-text>
                  <v-card-actions>
                    <v-list-item
                      three-line
                      class="grow"
                    >
                      <v-list-item-avatar color="grey darken-3">
                        <v-img
                          class="elevation-6"
                          src="../../assets/avatar.svg"
                        />
                      </v-list-item-avatar>
                      <v-list-item-content>
                        <v-list-item-title>{{ berita.namaUser }}</v-list-item-title>
                        <v-list-item-subtitle>
                          {{ berita.namaOrganisasi }}
                        </v-list-item-subtitle>
                        <v-list-item-subtitle>
                          {{ berita.publishTimeStr }}
                        </v-list-item-subtitle>
                      </v-list-item-content>
                    </v-list-item>
                  </v-card-actions>
                </v-card>
              </v-col>
            </v-row>
          </template>
          <template v-slot:no-data>
            <div class="text-center pt-5 pb-5">
              <span>Tidak ada berita</span>
            </div>
          </template>
          <template v-slot:no-results>
            <div class="text-center pt-5 pb-5">
              <span>Tidak ada berita yang sesuai dengan kriteria pencarian</span>
            </div>
          </template>
          <template v-slot:footer>
            <v-row
              v-if="items.length !== 0"
              class="mt-2"
              align="center"
              justify="center"
            >
              <span class="grey--text">Items per page</span>
              <v-menu offset-y>
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    dark
                    text
                    color="primary"
                    class="ml-2"
                    v-bind="attrs"
                    v-on="on"
                  >
                    {{ itemsPerPage }}
                    <v-icon>mdi-chevron-down</v-icon>
                  </v-btn>
                </template>
                <v-list>
                  <v-list-item
                    v-for="(number, index) in itemsPerPageArray"
                    :key="index"
                    @click="updateItemsPerPage(number)"
                  >
                    <v-list-item-title>{{ number }}</v-list-item-title>
                  </v-list-item>
                </v-list>
              </v-menu>

              <v-spacer />

              <span
                class="mr-4 grey--text"
              >
                Page {{ page }} of {{ numberOfPages }}
              </span>
              <v-btn
                fab
                dark
                color="success"
                class="mr-1"
                @click="formerPage"
              >
                <v-icon>mdi-chevron-left</v-icon>
              </v-btn>
              <v-btn
                fab
                dark
                color="success"
                class="ml-1"
                @click="nextPage"
              >
                <v-icon>mdi-chevron-right</v-icon>
              </v-btn>
            </v-row>
          </template>
        </v-data-iterator>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import { apiServer } from '../../setup-axios'

  export default {
    data: () => ({
      itemsPerPageArray: [3, 6, 9],
      search: '',
      filter: {},
      sortDesc: false,
      page: 1,
      itemsPerPage: 3,
      sortBy: 'judul',
      keys: [
        { text: 'Judul', value: 'judul' },
        { text: 'Institusi', value: 'namaOrganisasi' },
        { text: 'Diterbitkan Oleh', value: 'namaUser' },
        { text: 'Waktu', value: 'publishTimeStr' },
      ],
      items: [],
    }),

    computed: {
      numberOfPages () {
        return Math.ceil(this.items.length / this.itemsPerPage)
      },
    },

    created () {
      this.getData()
    },

    methods: {
      nextPage () {
        if (this.page + 1 <= this.numberOfPages) this.page += 1
      },
      formerPage () {
        if (this.page - 1 >= 1) this.page -= 1
      },
      updateItemsPerPage (number) {
        this.itemsPerPage = number
      },
      getData () {
        apiServer
          .get('/berita/' + this.$store.state.user.userId + '/' + this.$store.state.user.role.roleCode)
          .then(response => {
            this.items = response.data
          })
          .catch(error => {
            console.log('There was an error:', error.response) // Logs out the error
          })
      },
      viewBerita (item) {
        this.$router.push('/berita/detail-nonadmin/' + item.id)
      },
    },

  }
</script>

<style>
</style>
