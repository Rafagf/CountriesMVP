package com.rafagarcia.countries.main.countrieslist;

/**
 * Created by Rafa on 20/03/2018.
 */

public class CountryListViewModel {

    private String name;
    private String alpha2Code;
    private String continent;
    private String population;

    public CountryListViewModel(CountryListViewModelBuilder builder) {
        name = builder.name;
        alpha2Code = builder.alpha2Code;
        continent = builder.continent;
        population = builder.population;
    }

    public String getName() {
        return name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public String getContinent() {
        return continent;
    }

    public String getPopulation() {
        return population;
    }

    public static class CountryListViewModelBuilder {
        private String name;
        private String alpha2Code;
        private String continent;
        private String population;

        public CountryListViewModel build() {
            return new CountryListViewModel(this);
        }

        public CountryListViewModelBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CountryListViewModelBuilder setAlpha2Code(String alpha2Code) {
            this.alpha2Code = alpha2Code;
            return this;
        }

        public CountryListViewModelBuilder setContinent(String continent) {
            this.continent = continent;
            return this;
        }

        public CountryListViewModelBuilder setPopulation(String population) {
            this.population = population;
            return this;
        }
    }
}
