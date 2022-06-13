<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="dokumenOrganisasi"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Dokumen Institusi</v-toolbar-title>
          </v-toolbar>
          <v-divider></v-divider>
          <v-toolbar flat class="mt-1">
            <v-text-field
              v-model="search"
              append-icon="mdi-magnify"
              label="Search"
              single-line
              hide-details
              class="search"
            ></v-text-field>
            <div class="flex-grow-1"></div>
            <v-btn color="blue" dark class="mb-2" @click="addNew()">Tambah</v-btn>
          </v-toolbar>
        </template>

        <template v-slot:item.isActive="{ item }">
          <v-icon>{{ item.isActive ? "mdi-check" : "mdi-minus" }}</v-icon>
        </template>

        <template v-slot:item.action="{ item }" style="width: 10%">
          <v-btn class="pl-0" small color="blue darken-1" text @click="editItem(item)">Ubah</v-btn>
          <v-btn small color="blue darken-1" text @click="deleteItem(item)">Hapus</v-btn>
          <v-btn small color="blue darken-1" text @click="downloadItem(item)">Download</v-btn>
        </template>
        <template v-slot:no-data></template>
      </v-data-table>

      <v-snackbar v-model="snackbar" :color="snackbarColor" :timeout="2000" :top="true">
        {{ snackbarText }}
        <v-btn dark text @click="snackbar = false">Close</v-btn>
      </v-snackbar>
    </div>
  </v-container>
</template>

<script>
import { apiServer, fileUrl } from "../../setup-axios";
import { required } from "vuelidate/lib/validators";

export default {
  data: () => ({
    search: "",
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    isLoading: true,
    headers: [
      { text: "Judul", value: "judul" },
      { text: "Nama Jenis Dokumen", value: "namaJenisDokumen" },
      { text: "Nama File", value: "namaFile" },
      { text: "Nomor Dokumen", value: "nomorDokumen" },
      { text: "Tahun Terbit", value: "tahunTerbit" },
      { text: "Aksi", value: "action", sortable: false }
    ],
    dokumenOrganisasi: []
  }),

  watch: {
    dialog(val) {
      val || this.close();
    }
  },

  created() {
    if (this.$store.state.dokumenOrganisasi.message != null) {
      this.showMessage();
    }
    this.getData();
  },

  methods: {
    showMessage() {
      this.showSnackbar(
        this.$store.state.dokumenOrganisasi.snackbarColor,
        this.$store.state.dokumenOrganisasi.message
      );
      this.$store.dispatch("setDokumenOrganisasi", {
        snackbarColor: null,
        message: null
      });
    },
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/dokumen-organisasi/" + this.$store.state.user.organizationId)
        .then(response => {
          this.dokumenOrganisasi = response.data;
          console.log(response.data);
          this.isLoading = false;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
    deleteData(id) {
      apiServer
        .delete("/dokumen-organisasi-file/" + id)
        .then(response => {
          apiServer
            .delete(
              "/dokumen-organisasi/" +
                id +
                "/" +
                this.$store.state.user.username
            )
            .then(response => {
              this.getData();
              this.showSnackbar("success", "Delete data successful");
            })
            .catch(error => {
              console.log("There was an error:", error.response);
              this.showSnackbar(
                "error",
                "Error, please contact your administrator!"
              );
            });
        })
        .catch(error => {
          this.showSnackbar(
            "error",
            "Error, please contact your administrator!"
          );
        });
    },
    editItem(item) {
      console.log("find id = " + item.id);
      this.$store.dispatch("setDokumenOrganisasi", item);
      this.$router.replace("/dokumen-organisasi-update");
    },
    deleteItem(item) {
      confirm("Are you sure you want to delete this item?") &&
        this.deleteData(item.id);
    },
    showSnackbar(color, message) {
      console.log("snack " + color + " - " + message);
      this.snackbar = true;
      this.snackbarText = message;
      this.snackbarColor = color;
    },
    downloadItem(item) {
      apiServer.post("/dokumen-organisasi/download/" + item.id).then(resp => {
        let ticket = resp.data.ticket;
        let root = window.location.protocol + "//" + window.location.host;
        window.location =
          fileUrl +
          "/download/dokumen-organisasi/" +
          item.id +
          "?ticket=" +
          ticket;
      });
    },
    addNew() {
      this.$router.replace("/dokumen-organisasi-add");
    }
  }
};
</script>

