package persistencia.dao;

public interface DAO<T, K> extends CanInsert<T>, CanUpdate<T>, CanDelete<T>, CanList<T>, CanGet<T, K> { }
