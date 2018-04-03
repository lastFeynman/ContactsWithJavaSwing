package hwadee.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

import com.qt.datapicker.DatePicker;

public class DateObserver extends JButton implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void update(Observable o, Object arg) {
		Calendar calendar = (Calendar) arg;
	    DatePicker dp = (DatePicker) o;
	    setText(dp.formatDate(calendar,"yyyy-MM-dd"));
	}

	public DateObserver() {
	    final Observer obs = this;
	    final JButton tf = this;
	    this.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseReleased(MouseEvent e) {
	            if(tf!=null){
	                DatePicker dp = new DatePicker(obs);
	                Date selectedDate = dp.parseDate(tf.getText());
	                dp.setSelectedDate(selectedDate);
	                dp.start(tf);
	            }
	        }
	    });
	}
}
