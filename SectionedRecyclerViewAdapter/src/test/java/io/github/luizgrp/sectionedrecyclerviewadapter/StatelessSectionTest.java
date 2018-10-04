package io.github.luizgrp.sectionedrecyclerviewadapter;

import androidx.appcompat.widget.RecyclerView;
import android.view.View;

import org.junit.Test;

/*
 * Unit tests for StatelessSection
 */
@SuppressWarnings({"PMD.MethodNamingConventions"})
public class StatelessSectionTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withLoadingResource_throwsException() {
        // Given
        final int itemId = 1;
        final int loadingId = 2;

        SectionParameters sectionParameters = SectionParameters.builder()
                .itemResourceId(itemId)
                .loadingResourceId(loadingId)
                .build();

        // When
        getStatelessSection(sectionParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withLoadingViewProvided_throwsException() {
        // Given
        final int itemId = 1;

        SectionParameters sectionParameters = SectionParameters.builder()
                .itemResourceId(itemId)
                .loadingViewWillBeProvided()
                .build();

        // When
        getStatelessSection(sectionParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withFailedResource_throwsException() {
        // Given
        final int itemId = 1;
        final int failedId = 2;

        SectionParameters sectionParameters = SectionParameters.builder()
                .itemResourceId(itemId)
                .failedResourceId(failedId)
                .build();

        // When
        getStatelessSection(sectionParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withFailedViewProvided_throwsException() {
        // Given
        final int itemId = 1;

        SectionParameters sectionParameters = SectionParameters.builder()
                .itemResourceId(itemId)
                .failedViewWillBeProvided()
                .build();

        // When
        getStatelessSection(sectionParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withEmptyResource_throwsException() {
        // Given
        final int itemId = 1;
        final int emptyId = 2;

        SectionParameters sectionParameters = SectionParameters.builder()
                .itemResourceId(itemId)
                .emptyResourceId(emptyId)
                .build();

        // When
        getStatelessSection(sectionParameters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_withEmptyViewProvided_throwsException() {
        // Given
        final int itemId = 1;

        SectionParameters sectionParameters = SectionParameters.builder()
                .itemResourceId(itemId)
                .itemResourceId(itemId)
                .emptyViewWillBeProvided()
                .build();

        // When
        getStatelessSection(sectionParameters);
    }

    private StatelessSection getStatelessSection(SectionParameters sectionParameters) {
        return new StatelessSection(sectionParameters) {
            @Override
            public int getContentItemsTotal() {
                return 0;
            }

            @Override
            public RecyclerView.ViewHolder getItemViewHolder(View view) {
                return null;
            }

            @Override
            public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

            }
        };
    }
}
