<template>
  <v-container fluid>
    <div>
      <v-data-table
        :headers="headers"
        :items="employee"
        sort-by="calories"
        class="elevation-1"
        :loading="isLoading"
        loading-text="Loading... Please wait"
      >
        <template v-slot:top>
          <v-toolbar flat color="white">
            <v-toolbar-title>My CRUD</v-toolbar-title>
            <v-divider class="mx-4" inset vertical></v-divider>
            <div class="flex-grow-1"></div>
            <v-dialog v-model="dialog" max-width="500px">
              
             <template v-slot:activator="{ on }">
                <v-btn color="primary" dark class="mb-2" v-on="on">Add New</v-btn>
              </template>

              <v-card>
                <v-card-title>
                  <span class="headline">{{ formTitle }}</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field v-model="editedItem.name" label="Employee name"></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field v-model="editedItem.address" label="Address"></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field v-model="editedItem.role" label="Role"></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field v-model="editedItem.age" label="Age"></v-text-field>
                      </v-col>

                      <!-- Date Picker Toogle -->
                      <v-col cols="12" sm="6" md="5">
                        <v-menu
                          v-model="menu"
                          :close-on-content-click="false"
                          :nudge-right="40"
                          transition="scale-transition"
                          offset-y
                          min-width="290px"
                        >
                          <template v-slot:activator="{ on }">
                            <v-text-field
                              v-model="computedDateOfBirth"
                              label="Date of Birth"
                              prepend-icon="mdi-calendar-range"
                              readonly
                              v-on="on"
                            ></v-text-field>
                          </template>
                          <v-date-picker
                            v-model="editedItem.dateOfBirthFormattedDtp"
                            @input="menu = false"
                          ></v-date-picker>
                        </v-menu>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <div class="flex-grow-1"></div>
                  <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                  <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>
        <template v-slot:item.action="{ item }">
          <v-icon small class="mr-2" @click="editItem(item)">mdi-pencil</v-icon>
          <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
        </template>
        <template v-slot:no-data></template>
      </v-data-table>

      <v-snackbar
        v-model="snackbar"
        :color="snackbarColor"
        :timeout="2000"
        :top="true"
      >
        {{ snackbarText }}
        <v-btn
          dark
          text
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </v-snackbar>
    </div>
  </v-container>
</template>

<script>
import axios from "axios";
import moment from "moment";
import AxiosService from '@/services/AxiosService.js';

export default {
  data: () => ({
    snackbar: false,
    snackbarText: '',
    snackbarColor: '',
    isLoading: true,
    menu: false,
    dialog: false,
    employee: [],
    headers: [
      {
        text: "Name",
        align: "left",
        sortable: false,
        value: "name"
      },
      { text: "Address", value: "address" },
      { text: "Age", value: "age" },
      { text: "Role", value: "role" },
      { text: "Date of Birth", value: "dateOfBirthFormattedTxt" },
      { text: "Actions", value: "action", sortable: false }
    ],
    editedIndex: -1,
    editedItem: {
      name: "",
      address: "",
      role: "",
      age: "",
      dateOfBirth: "",
      dateOfBirthFormattedTxt: "",
      dateOfBirthFormattedDtp: "",
      userId: '',
      userName: ''
    },
    defaultItem: {
      name: "",
      address: "",
      role: "",
      age: "",
      dateOfBirth: ""
    }
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "New Employee" : "Edit Employee";
    },
    computedDateOfBirth() {
      this.editedItem.dateOfBirth = this.editedItem.dateOfBirthFormattedDtp;
      return this.editedItem.dateOfBirthFormattedDtp
        ? moment(this.editedItem.dateOfBirthFormattedDtp).format("DD-MM-YYYY")
        : "";
    }
  },

  watch: {
    dialog(val) {
      val || this.close();
    }
  },

  created() {
    this.getData();
  },

  methods: {
    getData() {
      this.isLoading = true;
      console.log(this.isLoading);
      axios
      .get("http://localhost:8001/test")
        .then(response => {
          this.employee = response.data;
          console.log(response.data); // For now, logs out the response
          response.data.forEach(this.formattingDate);
          this.isLoading = false;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
    postData() {
     axios
        .post("http://localhost:8001/employee", this.editedItem)
        .then(response => {
         this.getData();
         this.showSnackbar("success", "Add new data successful");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar("error", "Error, please contact your administrator!");
        });
    },
    deleteData(id) {
      axios
        .delete("http://localhost:8001/employee/" + id)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Delete data successful");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar("error", "Error, please contact your administrator!");
        });
    },
    updateData() {
      axios
        .put("http://localhost:8001/employee", this.editedItem)
        .then(response => {
          this.getData();
          this.showSnackbar("success", "Update data successful");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
          this.showSnackbar("error", "Error, please contact your administrator!");
        });
    },
    editItem(item) {
      this.editedIndex = this.employee.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      confirm("Are you sure you want to delete this item?") &&
        this.deleteData(item.id);
    },
    close() {
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    save() {
      if (this.editedIndex > -1) {
        this.updateData();
      } else {
        this.postData();
      }
      this.close();
    },
    showSnackbar(color, message){
      console.log("snack " + color + " - " + message);
      this.snackbar = true;
      this.snackbarText = message;
      this.snackbarColor = color;
    },
    formattingDate(value, index, array) {
      value.dateOfBirthFormattedTxt = value.dateOfBirth
        ? moment(value.dateOfBirth).format("DD-MM-YYYY")
        : "";
      value.dateOfBirthFormattedDtp = value.dateOfBirth
        ? moment(value.dateOfBirth).format("YYYY-MM-DD")
        : "";
    }
  }
};
</script>