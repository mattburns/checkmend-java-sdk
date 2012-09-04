package uk.co.mattburns.checkmend;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class Property {
    private long personid;
    private Category category;
    private String make;
    private String model;
    private String description;
    private List<String> serials;

    enum Category {
        Other(0), Mobile(1), Laptop(2), Console(3), Satnav(4), Camera(5), Jewellery(
                6);
        private int categoryCode;

        private Category(int categoryCode) {
            this.categoryCode = categoryCode;
        }

        public int categoryCode() {
            return categoryCode;
        }
    }

    private Property(long personid, Category category, String make,
            String model, String description, List<String> serials) {
        this.personid = personid;
        this.category = category;
        this.make = make;
        this.model = model;
        this.description = description;
        this.serials = serials;
    }

    public long getPersonid() {
        return personid;
    }

    public Category getCategory() {
        return category;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getSerials() {
        return serials;
    }

    public String toJson() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Category.class,
                        new JsonSerializer<Category>() {
                            @Override
                            // use the category code
                            public JsonElement serialize(Category category,
                                    Type arg1, JsonSerializationContext arg2) {
                                return new Gson().toJsonTree(""
                                        + category.categoryCode());
                            }
                        })
                .registerTypeAdapter(List.class, new JsonSerializer<List<?>>() {
                    @Override
                    // Don't put single element lists in a json array
                    public JsonElement serialize(List<?> list, Type arg1,
                            JsonSerializationContext arg2) {
                        if (list.size() == 1) {
                            return new Gson().toJsonTree(list.get(0));
                        } else {
                            return new Gson().toJsonTree(list);
                        }
                    }
                }).create();

        return gson.toJson(this);
    }

    public static class PropertyBuilder {
        private long personid;
        private Category category;
        private String make;
        private String model;
        private String description;
        private List<String> serials;

        public PropertyBuilder(long personid, Category category, String make,
                String... serials) {
            this.personid = personid;
            this.category = category;
            this.make = make;
            this.serials = Arrays.asList(serials);
        }

        public PropertyBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public PropertyBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Property build() {
            return new Property(personid, category, make, model, description,
                    serials);
        }
    }
}
