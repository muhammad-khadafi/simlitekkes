><template>
  <v-container fluid>
    <material-card
      color="green"
      title="Detail Berita"
    >
      <v-row justify="center">
        <v-col cols="11">
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
            <v-card-subtitle> {{ berita.judul }} </v-card-subtitle>
            <v-card-text>
              <v-textarea
                v-model="berita.isi"
                :auto-grow="autoGrow"
                rows="10"
                outlined
                readonly
              />
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
      <v-row
        justify="end"
        class="ml-12 mt-6"
      >
        <v-btn
          color="red"
          class="mb-2 white--text mr-2"
          @click="back"
        >
          Kembali ke Daftar Berita
        </v-btn>
      </v-row>
    </material-card>
  </v-container>
</template>

<script>
  import { apiServer } from '../../setup-axios'

  export default {
    data: () => ({
      idBerita: '',
      berita: {
        id: '',
        idOrganisasi: '',
        namaOrganisasi: '',
        judul: '',
        isi: '',
        isPublished: true,
        userPublish: '',
        namaUser: '',
        publishTimeStr: '',
      },
      autoGrow: true,
    }),

    created () {
      this.idBerita = this.$route.params.id
      this.getData()
    },

    methods: {
      back () {
        this.$router.replace('/berita')
      },
      getData () {
        apiServer
          .get('/berita/detail/' + this.idBerita)
          .then(response => {
            this.berita = response.data
          })
          .catch(error => {
            console.log('error at getData for Berita:', error)
            this.showSnackbar('error', 'Error, Harap hubungi Administrator!')
          })
      },
    },
  }
</script>

<style>

</style>
