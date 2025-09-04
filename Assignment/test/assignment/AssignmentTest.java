
package assignment;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTest {
    
   @BeforeEach
    void setup() {
        Series.initializeRepository(4);
        Series.addSeries(new Series(101, "Extreme Sports", 18, 10));
        Series.addSeries(new Series(102, "Bargain Hunters", 13, 8));
    }

    @Test
    void addSeries_shouldAddUnique() {
        boolean ok = Series.addSeries(new Series(103, "Home Cooking", 16, 12));
        assertTrue(ok);
        assertEquals(3, Series.getCount());
        assertNotNull(Series.findById(103));
    }

    @Test
    void addSeries_shouldRejectDuplicateId() {
        boolean ok = Series.addSeries(new Series(101, "Duplicate", 16, 5));
        assertFalse(ok);
        assertEquals(2, Series.getCount());
    }

    @Test
    void findById_shouldReturnCorrectSeries() {
        Series s = Series.findById(101);
        assertNotNull(s);
        assertEquals("Extreme Sports", s.getName());
        assertEquals(10, s.getNumberOfEpisodes());
    }

    @Test
    void updateSeries_shouldModifyFields() {
        boolean ok = Series.updateSeries(102, "Bargain Pros", 16, 12);
        assertTrue(ok);
        Series s = Series.findById(102);
        assertEquals("Bargain Pros", s.getName());
        assertEquals(16, s.getAgeRestriction());
        assertEquals(12, s.getNumberOfEpisodes());
    }

    @Test
    void updateSeries_shouldAllowPartialUpdate() {
        boolean ok = Series.updateSeries(101, null, null, 11);
        assertTrue(ok);
        Series s = Series.findById(101);
        assertEquals("Extreme Sports", s.getName()); // unchanged
        assertEquals(18, s.getAgeRestriction());     // unchanged
        assertEquals(11, s.getNumberOfEpisodes());   // changed
    }

    @Test
    void deleteById_shouldRemoveAndShift() {
        boolean ok = Series.deleteById(101);
        assertTrue(ok);
        assertEquals(1, Series.getCount());
        assertNull(Series.findById(101));
        assertNotNull(Series.findById(102));
    }
    
}
