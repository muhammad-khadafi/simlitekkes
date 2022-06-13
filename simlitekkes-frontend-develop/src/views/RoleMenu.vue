<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="roleMenu"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
        :search="search"
      >
        <template v-slot:top>
          <v-toolbar>
            <v-toolbar-title>List of Role Menu</v-toolbar-title>
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
                <v-btn color="blue" dark class="mb-2" v-on="on">Add New</v-btn>
              </template>

              <v-card>
                <v-card-title>
                  <span class="headline">{{ formTitle }}</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12">
                        <v-select
                          :items="role"
                          item-text="roleName"
                          item-value="id"
                          label="Role"
                          :clearable="true"
                          v-model="editedItem.idRole"
                          required
                          @input="$v.editedItem.idRole.$touch()"
                          @blur="$v.editedItem.idRole.$touch()"
                          :error-messages="roleErrors"
                        ></v-select>
                      </v-col>

                      <v-col cols="12">
                        <v-select
                          :items="menu"
                          multiple
                          chips
                          item-text="menuName"
                          item-value="id"
                          label="Menus"
                          v-model="editedItem.idMenus"
                          required
                          @input="$v.editedItem.idMenus.$touch()"
                          @blur="$v.editedItem.idMenus.$touch()"
                          :error-messages="menusErrors"
                        ></v-select>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions class="mb-7">
                  <div class="flex-grow-1"></div>
                  <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                  <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:item.isActive="{ item }">
          <v-icon>{{ item.isActive ? "mdi-check" : "mdi-minus" }}</v-icon>
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
import { required } from "vuelidate/lib/validators";

export default {
  data: () => ({
    search: "",
    snackbar: false,
    snackbarText: "",
    snackbarColor: "",
    isLoading: true,
    menu: false,
    dialog: false,
    headers: [
      { text: "Role Name", value: "roleName" },
      { text: "Menus", value: "menusStr" },
      { text: "Actions", value: "action", sortable: false }
    ],
    editedIndex: -1,
    editedItem: {
      idRole: "",
      idMenus: [],
      username: ""
    },
    defaultItem: {
      idRole: "",
      idMenus: [],
      username: ""
    },
    role: [],
    menu: [],
    roleMenu: []
  }),

  validations: {
    editedItem: {
      idRole: {
        required
      },
      idMenus: {
        required
      }
    }
  },

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "Add New Role Menu" : "Edit Role Menu";
    },
    roleErrors() {
      const errors = [];
      if (!this.$v.editedItem.idRole.$dirty) return errors;
      !this.$v.editedItem.idRole.required && errors.push("Role is required.");
      return errors;
    },
    menusErrors() {
      const errors = [];
      if (!this.$v.editedItem.idMenus.$dirty) return errors;
      !this.$v.editedItem.idMenus.required && errors.push("Menu is required.");
      return errors;
    }
  },

  watch: {
    dialog(val) {
      val || this.close();
    }
  },

  created() {
    this.getData();
    this.getRole();
    this.getMenu();
  },

  methods: {
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/role-menu")
        .then(response => {
          this.roleMenu = response.data;
          console.log(response.data);
          this.isLoading = false;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
    getMenu() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/menu")
        .then(response => {
          this.menu = response.data;
          console.log(response.data);
          this.isLoading = false;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
    getRole() {
      this.isLoading = true;
      console.log(this.isLoading);
      apiServer
        .get("/role")
        .then(response => {
          this.role = response.data;
          console.log(response.data);
          this.isLoading = false;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
    postData() {
      apiServer
        .post("/role-menu", this.editedItem)
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
    deleteData(idRole) {
      apiServer
        .delete("/role-menu/" + idRole + "/" + this.$store.state.user.name)
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
    },
    updateData() {
      apiServer
        .put("/role-menu", this.editedItem)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Update data successful");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar(
            "error",
            "Error, please contact your administrator!"
          );
        });
    },
    editItem(item) {
      console.log("DEBUG : ");
      console.log(item);
      console.log(this.menu);
      this.editedIndex = this.roleMenu.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      confirm("Are you sure you want to delete this item?") &&
        this.deleteData(item.idRole);
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
      this.$v.$touch();
      if (!this.$v.$invalid) {
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

