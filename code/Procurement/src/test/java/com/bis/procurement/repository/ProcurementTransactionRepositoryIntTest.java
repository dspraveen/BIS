package com.bis.procurement.repository;

import com.bis.domain.ProcurementTransaction;
import com.bis.testcommon.BaseIntTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcurementTransactionRepositoryIntTest extends BaseIntTest {
    @Autowired
    private ProcurementTransactionRepository repository;

    @Test
    public void shouldPerformCRUDOnOItem(){
        /*Item item = new ItemBuilder().withDefaults().build();*/
        /*ProcurementTransaction transaction = new ProcurementTransaction();
        transaction.setVendorId(9);
        transaction.setDate(Calendar.getInstance().getTime());
        transaction.setTotalAmount(100F);
        transaction.setTransactionType('O');
        PtDetails one = new PtDetails();
        one.setQuantity(10);
        one.setItemCode(48);
        one.setDateOfPublishing(Calendar.getInstance().getTime());

        PtDetails two = new PtDetails();
        two.setQuantity(10);
        two.setItemCode(49);
        two.setDateOfPublishing(Calendar.getInstance().getTime());
        transaction.getTransactionDetails().add(one);
        transaction.getTransactionDetails().add(two);
        repository.save(transaction);*/

        ProcurementTransaction procurementTransaction = repository.get(8);
        System.out.println("");


        /*Item dbItem = itemRepository.get(item.getItemCode());
        assertEquals(item, dbItem);

        dbItem.setDescription("new");
        itemRepository.update(dbItem);

        Item updatedItem = itemRepository.get(dbItem.getItemCode());
        assertEquals("new", updatedItem.getDescription());

        itemRepository.delete(item);
        assertNull(itemRepository.get(item.getItemCode()));*/
    }
}
