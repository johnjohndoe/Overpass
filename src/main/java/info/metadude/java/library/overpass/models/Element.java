package info.metadude.java.library.overpass.models;

public class Element {

    public String type;

    public long id;

    public double lat;

    public double lon;

    public Tags tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return id == element.id && Double.compare(element.lat, lat) == 0 &&
                Double.compare(element.lon, lon) == 0 &&
                !(type != null ? !type.equals(element.type) : element.type != null) &&
                !(tags != null ? !tags.equals(element.tags) : element.tags != null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type != null ? type.hashCode() : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Element{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", tags=" + tags +
                '}';
    }

}
