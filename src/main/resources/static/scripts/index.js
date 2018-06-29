var app = new Vue({
  el: '#app',
  data: {
    errorFields: [],
    model: {
      betrag: null,
      datum: null,
      berechnungsEnde: null,
      zahlungen: [],
      sollaenderungen: [],
      zinstyp: {
        abrundenAuf: '50',
        zinssaetze: [ {
          jahreszins: '7.5',
          gueltigAb: '2000-01-01',
          gueltigBis: '2020-12-31',
        } ]
      },
    },
    result: null,
    enums: {}
  },
  created: function() {
    axios.get('/enums')
    .then(function(response) {
      app.enums = response.data;
    });
  },
  methods: {

    executeBerechnen: function() {
      var urlBerechnen = '/berechnen';
      return axios.post(urlBerechnen, this.model);
    },

    validateList: function(list, id) {
      for (var i = 0; i < list.length; i++) {
        var elem = list[i];
        if (!elem.betrag || elem.betrag === 0 || !elem.datum) {
          this.errorFields.push(id + '_' + i);
        }
      }
    },

    validateModel: function() {
      if (!this.model.betrag || this.model.betrag < 0.01) {
        this.errorFields.push('forderung_betrag');
      }
      if (!this.model.datum) {
        this.errorFields.push('forderung_datum');
      }
      app.validateList(this.model.zahlungen, 'zahlung');
      app.validateList(this.model.sollaenderungen, 'sollaenderung');
    },

    handleErrors: function(removeClass) {
      for (var i = 0; i < this.errorFields.length; i++) {
        var element = document.getElementById(this.errorFields[i]);
        if (element) {
          if (removeClass) {
            element.classList.remove('error');
          } else {
            element.classList.add('error');
          }
        }
      }

      if (removeClass) {
        this.errorFields = [];
      }
    },

    removeErrors: function() {
      app.handleErrors(true);
    },

    showErrors: function() {
      app.handleErrors(false);
    },

    clickBerechnen: function() {
      this.result = null;
      app.removeErrors();
      app.validateModel();
      app.showErrors();

      if (this.errorFields.length === 0) {
        app.executeBerechnen()
        .then(function(response) {
          app.result = response.data;
        });
      }
    },

    addZahlung: function() {
      this.model.zahlungen.push(
        { betrag: null, datum: null }
      );
    },

    deleteZahlung: function(index) {
      this.model.zahlungen.splice(index, 1);
    },

    getIdZahlung: function(index) {
      return 'zahlung_' + index;
    },

    addSollaenderung: function() {
      this.model.sollaenderungen.push(
        { betrag: null, datum: null }
      );
    },

    deleteSollaenderung: function(index) {
      this.model.sollaenderungen.splice(index, 1);
    },

    getIdSollaenderung: function(index) {
      return 'sollaenderung_' + index;
    },

    addZinssatz: function() {
      this.model.zinstyp.zinssaetze.push({
        jahreszins: null,
        gueltigAb: null,
        gueltigBis: null
      });
    },

    deleteZinssatz: function(index) {
      this.model.zinstyp.zinssaetze.splice(index, 1);
    },

    getIdZinssatz: function(index) {
      return 'zinssatz_' + index;
    },

  }
});
