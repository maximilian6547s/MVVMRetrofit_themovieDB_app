package com.example.mvvmretrofitdemo.model;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieApiResponse implements Parcelable {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    public final static Parcelable.Creator<MovieApiResponse> CREATOR = new Creator<MovieApiResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieApiResponse createFromParcel(Parcel in) {
            return new MovieApiResponse(in);
        }

        public MovieApiResponse[] newArray(int size) {
            return (new MovieApiResponse[size]);
        }

    };

    protected MovieApiResponse(Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.results, (com.example.mvvmretrofitdemo.model.Result.class.getClassLoader()));
    }

    public MovieApiResponse() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }
}