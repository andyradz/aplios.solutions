package com.codigo.aplios.sdk.core.hamcrest;

import java.util.Objects;

public class LevenshteinDistance {

	private LevenshteinDistance() {

	}

	public static int distance(final CharSequence lhs, final CharSequence rhs) {

		Objects.requireNonNull(lhs);
		Objects.requireNonNull(rhs);

		final int len0 = lhs.length() + 1;
		final int len1 = rhs.length() + 1;

		// the array of distances
		int[] cost = new int[len0];
		int[] newcost = new int[len0];

		// initial cost of skipping prefix in String s0
		for (int i = 0; i < len0; i++)
			cost[i] = i;

		// dynamically computing the array of distances

		// transformation cost for each letter in s1
		for (int j = 1; j < len1; j++) {
			// initial cost of skipping prefix in String s1
			newcost[0] = j;

			// transformation cost for each letter in s0
			for (int i = 1; i < len0; i++) {
				// matching current letters in both strings
				final int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

				// computing cost for each transformation
				final int cost_replace = cost[i - 1] + match;
				final int cost_insert = cost[i] + 1;
				final int cost_delete = newcost[i - 1] + 1;

				// keep minimum cost
				newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
			}

			// swap cost/newcost arrays
			final int[] swap = cost;
			cost = newcost;
			newcost = swap;
		}

		// the distance is the cost for transforming all letters in both strings
		return cost[len0 - 1];
	}
}
