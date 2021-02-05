package dal.asd.catme.database;

public class DatabaseAbstractFactoryImpl implements IDatabaseAbstractFactory
{
    private IDatabaseAccess databaseAccess = null;

    public DatabaseAbstractFactoryImpl()
    {
        this.databaseAccess = new DatabaseAccess();
    }

    @Override
    public IDatabaseAccess makeDatabaseAccess()
    {
        return databaseAccess;
    }
}
