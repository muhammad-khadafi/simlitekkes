<template>
  <v-container fluid>
    <div>

      <material-card v-if="!hasAccess">
        <page403 />
      </material-card>

      <v-data-table
        v-if="hasAccess"
        :headers="headers"
        :items="jenisTkt"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>Daftar Jenis TKT</v-toolbar-title>
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
            <v-dialog v-model="dialog" max-width="500px">
              <template v-slot:activator="{ on }">
                <v-btn color="blue" dark class="mb-2" v-on="on">Tambah</v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <span class="headline">{{ formTitle }}</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.nama"
                          label="Nama*"
                          required
                          @input="$v.editedItem.nama.$touch()"
                          @blur="$v.editedItem.nama.$touch()"
                          :error-messages="namaErrors"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.tahun"
                          label="Tahun*"
                          required
                          @input="$v.editedItem.tahun.$touch()"
                          @blur="$v.editedItem.tahun.$touch()"
                          :error-messages="tahunErrors"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12" v-show="editedIndex > -1">
                        <v-checkbox v-model="editedItem.isActive" label="Status Aktif"></v-checkbox>
                      </v-col>
                    </v-row>
                  </v-container>

                  <v-card-actions>
                    <div class="flex-grow-1"></div>
                    <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                    <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                  </v-card-actions>
                </v-card-text>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:item.isActive="{ item }">{{ item.isActive ? "Aktif" : "Tidak Aktif" }}</template>

        <template v-slot:item.action="{ item }">
          <v-btn class="pl-0" small color="blue darken-1" text @click="editItem(item)">Edit</v-btn>
          <v-btn small color="blue darken-1" text @click="deleteItem(item)">Hapus</v-btn>
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
import { apiServer } from "../setup-axios";
import { required, numeric } from "vuelidate/lib/validators";

export default {
  data: () => ({
    search: "",
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    isLoading: true,
    dialog: false,
    headers: [
      { text: "Nama", value: "nama" },
      { text: "Tahun Penetapan", value: "tahun" },
      { text: "Status", value: "isActive" },
      { text: "Aksi", value: "action", sortable: false }
    ],
    editedIndex: -1,
    editedItem: {
      nama: "",
      tahun: "",
      isActive: "",
      username: ""
    },
    defaultItem: {
      nama: "",
      tahun: "",
      isActive: "",
      username: ""
    },
    jenisTkt: [],
    hasAccess: false
  }),

  validations: {
    editedItem: {
      nama: {
        required
      },
      tahun: {
        required,
        numeric
      }
    }
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Tambah Jenis TKT" : "Edit Jenis TKT";
    },
    namaErrors() {
      const errors = [];
      if (!this.$v.editedItem.nama.$dirty) return errors;
      !this.$v.editedItem.nama.required && errors.push("Harus di isi!");
      return errors;
    },
    tahunErrors() {
      const errors = [];
      if (!this.$v.editedItem.tahun.$dirty) return errors;
      !this.$v.editedItem.tahun.required && errors.push("Harus di isi!");
      !this.$v.editedItem.tahun.numeric &&
        errors.push("Tahun harus berisi angka");
      return errors;
    }
  },

  components: {
      Page403: () => import('@/views/403.vue'),
    },

  watch: {
    dialog(val) {
      val || this.close();
    }
  },

  created() {
    if (this.$store.state.user.organizationId == 1){
      this.hasAccess = true;
      this.getData();
    }
  },

  methods: {
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/jenis-tkt")
        .then(response => {
          this.jenisTkt = response.data;
          this.isLoading = false;
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.isLoading = false;
        });
    },
    close() {
      this.$v.$reset();
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    save() {
      this.editedItem.username = this.$store.state.user.name;
      this.$v.$touch();
      if (!this.$v.$invalid) {
        if (this.editedIndex > -1) {
          this.updateData();
        } else {
          this.postData();
        }
        this.close();
      }
    },
    editItem(item) {
      this.editedIndex = this.jenisTkt.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      this.editedItem.username = this.$store.state.user.name;
      confirm("Are you sure you want to delete this item?") &&
        this.deleteData(item.id);
    },
    deleteData(id) {
      apiServer
        .delete("/jenis-tkt/" + id + "/" + this.editedItem.username)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Delete data successful");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar("error", error.response.data);
        });
    },
    postData() {
      apiServer
        .post("/jenis-tkt", this.editedItem)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Add new data successful");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar(
            "error",
            "Error, please contact your administrator!"
          );
        });
    },
    updateData() {
      apiServer
        .put("/jenis-tkt", this.editedItem)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Edit data successful");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar(
            "error",
            "Error, please contact your administrator!"
          );
        });
    },
    showSnackbar(color, message) {
      console.log("snack " + color + " - " + message);
      this.snackbar = true;
      this.snackbarText = message;
      this.snackbarColor = color;
    }
  }
};
</script>

