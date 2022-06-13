<template>
  <v-container>
    <v-row>
      <v-col>
        <v-form>
          <v-file-input
            v-model="file"
            @change="onFilePicked"
            label="Attach File to Upload"
            name="fileDoc"
            light
          />
          <v-btn color="blue" type="submit" @click="save" class="white--text">Submit</v-btn>
        </v-form>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card>
          <v-toolbar>
            <v-toolbar-title>Your Dashboard</v-toolbar-title>

            <v-spacer></v-spacer>
          </v-toolbar>
        <v-data-table :headers="headers" :items="listOfFile" sort-by="name" class="elevation-1">
          <template v-slot:item.action="{ item }">
            <v-icon small class="mr-2" @click="downloadFile(item)">mdi-arrow-down-bold</v-icon>
            <v-icon small @click="deleteItem(item)">mdi-delete</v-icon>
          </template>
          <template v-slot:no-data></template>
        </v-data-table>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import {apiServer} from "../setup-axios"

export default {
  data() {
    return {
      file: null,
      search: "",
      listOfFile: [],
      headers: [
        { text: "Name", value: "fileName" },
        { text: "Path", value: "filePath" },
        { text: "Actions", value: "action", sortable: false }
      ]
    };
  },
  created() {
    this.getData();
  },
  methods: {
    onFilePicked(event) {
      console.log(event);
      let reader = new FileReader();
      reader.onload = () => {
        this.url = reader.result;
      };
      reader.readAsDataURL(this.file);
      console.log(this.file);
    },
    save() {
      this.postData();
    },
    postData() {
      const formData = new FormData();
      formData.append("file", this.file);
      formData.append("id", 7878);
      apiServer
        .post("/file/upload", formData)
        .then(response => {
          console.log("Success..");
        })
        .catch(error => {
          console.log("There was an error:", error.response);
        });
    },
    getData() {
      apiServer
        .get("/file")
        .then(response => {
          this.listOfFile = response.data;
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
          this.isLoading = false;
        });
    },
    downloadFile(item) {
        apiServer.post('/file/download/' + item.id)
          .then((resp) => {
            let ticket = resp.data.ticket
            let root = window.location.protocol + '//' + window.location.host
            window.location = 'http://localhost:8001/file/download/' + item.id + '?ticket=' + ticket
          })
    }
  }
};
</script>

<style>
  .file{
    color: black !important;
  }
</style>