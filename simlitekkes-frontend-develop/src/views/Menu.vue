<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="menu"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>List of Menu</v-toolbar-title>
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
            <v-dialog v-model="dialog" max-width="750px">
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
                          v-model="editedItem.menuCode"
                          label="Menu Code"
                          required
                          @input="$v.editedItem.menuCode.$touch()"
                          @blur="$v.editedItem.menuCode.$touch()"
                          :error-messages="menuCodeErrors"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.menuName"
                          label="Menu Name"
                          required
                          @input="$v.editedItem.menuName.$touch()"
                          @blur="$v.editedItem.menuName.$touch()"
                          :error-messages="menuNameErrors"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <v-select
                          :items="menuParent"
                          item-text="menuName"
                          item-value="id"
                          label="Menu Parent"
                          clearable
                          v-model="editedItem.idParent"
                        ></v-select>
                      </v-col>

                      <v-col cols="4">
                        <v-text-field
                          type="number"
                          v-model="editedItem.menuOrder"
                          label="Menu Order"
                          required
                          @input="$v.editedItem.menuOrder.$touch()"
                          @blur="$v.editedItem.menuOrder.$touch()"
                          :error-messages="menuOrderErrors"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <v-checkbox v-model="editedItem.isDisplayed" label="Is Display?"></v-checkbox>
                      </v-col>

                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.icon"
                          label="Icon"
                          @input="$v.editedItem.icon.$touch()"
                          @blur="$v.editedItem.icon.$touch()"
                          :error-messages="iconErrors"
                        ></v-text-field>
                      </v-col>

                      <v-col cols="12">
                        <v-text-field
                          v-model="editedItem.routerTo"
                          label="Router Path"
                          @input="$v.editedItem.routerTo.$touch()"
                          @blur="$v.editedItem.routerTo.$touch()"
                          :error-messages="routerToErrors"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <div class="flex-grow-1"></div>
                  <v-btn color="blue darken-1" text @click="close">Kembali</v-btn>
                  <v-btn color="blue darken-1" text @click="save">Simpan</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:item.isDisplayed="{ item }">
          <v-icon>{{ item.isDisplayed ? "mdi-check" : "mdi-minus" }}</v-icon>
        </template>

        <template v-slot:item.action="{ item }">
          <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
          <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
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
import { required, maxLength } from "vuelidate/lib/validators";

export default {
  data: () => ({
    search: "",
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    isLoading: true,
    // menu: false,
    dialog: false,
    employee: [],
    userweb: [],
    menu: [],
    menuParent: [],
    headers: [
      { text: "Menu Code", value: "menuCode" },
      { text: "Menu Name", value: "menuName" },
      { text: "Menu Parent", value: "menuParentName" },
      { text: "Menu Order", value: "menuOrder" },
      { text: "Is Display", value: "isDisplayed" },
      { text: "Icon", value: "icon" },
      { text: "Router Path", value: "routerTo" },
      { text: "Actions", value: "action", sortable: false }
    ],
    editedIndex: -1,
    editedItem: {
      menuCode: "",
      menuName: "",
      menuParent: "",
      idParent: "",
      menuOrder: "",
      isDisplayed: "",
      icon: "",
      routerTo: "",
      username: ""
    },
    defaultItem: {
      menuCode: "",
      menuName: "",
      menuParent: "",
      idParent: "",
      menuOrder: "",
      isDisplayed: "",
      icon: "",
      routerTo: "",
      username: ""
    },
    menuCodeEdited: ""
  }),

  validations: {
    editedItem: {
      menuCode: {
        required,
        maxLength: maxLength(50),
        async isUnique(value) {
          if (value === "") return true;

          if (value === this.menuCodeEdited) return true;

          for (var index in this.menu) {
            if (
              this.menu[index].menuCode.toUpperCase().trim() ===
              value.toUpperCase().trim()
            ) {
              return false;
            }
          }
          return true;
        }
      },
      menuName: {
        required,
        maxLength: maxLength(100)
      },
      menuOrder: {
        required
      },
      icon: {
        maxLength: maxLength(100)
      },
      routerTo: {
        maxLength: maxLength(200)
      }
    }
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Tambah Menu" : "Edit Menu";
    },
    menuCodeErrors() {
      const errors = [];
      if (!this.$v.editedItem.menuCode.$dirty) return errors;
      !this.$v.editedItem.menuCode.required &&
        errors.push("Menu code harus di isi");
      !this.$v.editedItem.menuCode.maxLength &&
        errors.push("Menu code tidak boleh lebih dari 50 karakter");
      !this.$v.editedItem.menuCode.isUnique &&
        errors.push("Menu code sudah terdaftar");
      return errors;
    },
    menuNameErrors() {
      const errors = [];
      if (!this.$v.editedItem.menuName.$dirty) return errors;
      !this.$v.editedItem.menuName.required &&
        errors.push("Menu name harus di isi");
      !this.$v.editedItem.menuName.maxLength &&
        errors.push("Menu name tidak boleh lebih dari 50 karakter");
      return errors;
    },
    menuOrderErrors() {
      const errors = [];
      if (!this.$v.editedItem.menuOrder.$dirty) return errors;
      !this.$v.editedItem.menuOrder.required &&
        errors.push("Menu order harus di isi");
      return errors;
    },
    iconErrors() {
      const errors = [];
      if (!this.$v.editedItem.icon.$dirty) return errors;
      !this.$v.editedItem.icon.maxLength &&
        errors.push("icon tidak boleh lebih dari 100 karakter");
      return errors;
    },
    routerToErrors() {
      const errors = [];
      if (!this.$v.editedItem.routerTo.$dirty) return errors;
      !this.$v.editedItem.routerTo.maxLength &&
        errors.push("Router path tidak boleh lebih dari 200 karakter");
      return errors;
    }
  },

  watch: {
    dialog(val) {
      val || this.close();
      if (val){
        this.getMenuParent();
      }
    }
  },

  created() {
    this.getData();
  },

  methods: {
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/menu")
        .then(response => {
          this.menu = response.data;
          console.log(response.data); // For now, logs out the response
          this.isLoading = false;
        })
        .catch(error => {
          console.log("There was an error: ", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
    getMenuParent() {
      apiServer
        .get("/menuParent")
        .then(response => {
          this.menuParent = response.data;
        })
        .catch(error => {
          console.log("There was an error: ", error.response);
        });
    },
    postData() {
      apiServer
        .post("/menu", this.editedItem)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Berhasil menambah data");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar(
            "error",
            "Error, silakan hubungi administrator anda"
          );
        });
    },
    deleteData(id) {
      apiServer
        .delete("/menu/" + id + "/" + this.$store.state.user.name)
        .then(response => {
          if (response.data == "HAS_USER_ROLE") {
            this.showSnackbar(
              "error",
              "Tidak dapat dihapus, Menu memiliki referensi pada role menu"
            );
          } else if (response.data == "HAS_CHILD") {
            this.showSnackbar(
              "error",
              "Tidak dapat dihapus, Menu memiliki referensi child menu"
            );
          } else {
            this.showSnackbar("success", "Berhasil menghapus data");
          }
          this.getData();
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar(
            "error",
            "Error, silakan hubungi administrator anda"
          );
        });
    },
    updateData() {
      apiServer
        .put("/menu", this.editedItem)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Berhasil mengubah data");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar(
            "error",
            "Error, silakan hubungi administrator anda"
          );
        });
    },
    editItem(item) {
      this.editedIndex = this.menu.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
      this.menuCodeEdited = this.editedItem.menuCode
    },

    deleteItem(item) {
      confirm("Apakah anda yakin akan menghapus data ini?") &&
        this.deleteData(item.id);
    },
    close() {
      this.$v.$reset();
      this.dialog = false;
      this.menuCodeEdited = ""
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    save() {
      this.$v.$touch();
      if (!this.$v.$anyError) {
        console.log("clicked2..");
        this.editedItem.username = this.$store.state.user.name;
        if (this.editedIndex > -1) {
          this.updateData();
        } else {
          this.postData();
        }
        this.close();
      }
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
