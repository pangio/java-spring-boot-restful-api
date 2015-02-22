package com.pangio.magazine.api.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for the {@link Magazine} pojo.
 * @author pangio
 */
public class MagazineTest {
	
	@Test
	public void canConstructAMagazineWithAName() {
		Magazine magazine = new Magazine("Weekend");
		assertEquals("Weekend", magazine.getName());
	}
}
